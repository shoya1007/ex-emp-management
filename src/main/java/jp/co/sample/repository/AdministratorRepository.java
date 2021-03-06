package jp.co.sample.repository;



import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;

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
	 * メールアドレスとパスワードから管理者情報を数得する
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		try {
		String sql = "select * from administrators where mail_address=:mailAddress and password=:password";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress ).addValue("password", password);
		
		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		
		return administrator;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Administrator findByMailAddress(String mailAddress) {
		try {
		String sql = "select * from administrators where mail_address=:mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		return administrator;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
}
