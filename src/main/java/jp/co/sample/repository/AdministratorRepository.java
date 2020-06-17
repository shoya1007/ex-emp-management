package jp.co.sample.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

import org.springframework.jdbc.core.RowMapper;

/**
 * administratorsテーブルを操作するリポジトリ
 * @author shoya
 * 
 */

@Repository
public class AdministratorRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER=(rs,i)->{
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	/**
	 * insertメソッド
	 * @param administrator
	 * administratorsテーブルに管理者情報を挿入する
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String sql = "insert into administrators (name,mail_address,password) "
				+ "values(:name,:mailAddress,:password)";
		
		template.update(sql, param);
				
	}
	
	/**
	 * findByMailAddressAndPasswordメソッド
	 * @param mailAddress
	 * @param password
	 * @return
	 * メーリアドレスとパスワードから管理者情報を数得する
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		String sql = "select * from administrators where mail_address=:mailAddress and password=:password";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress ).addValue("password", password);
		
		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administrator == null) {
			return null;
		}
		return administrator;
	}
}
