package jp.co.sample.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;
/**
 * 
 * @author shoya
 * 従業員関連機能の処理の制御を行うコントローラ
 */
@Repository
public class EmployeeRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacterstics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * findAllメソッド
	 * @return Employee型のリスト
	 * 従業員一覧情報を入社日順で取得する
	 * 従業員が存在しない場合、0件の従業員一覧を返す
	 */
	public List<Employee> findAll() {
		String sql = "select * from employees order by hire_date desc";

		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

		if (employeeList == null) {
			return Collections.emptyList();
		}

		return employeeList;
	}

	/**
	 * loadメソッド
	 * @param id
	 * @return employeeオブジェクト
	 * 主キーから従業員情報を取得する
	 */
	public Employee load(Integer id) {
		String sql = "select * from employees where id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return employee;
	}
	
	/**
	 * updateメソッド
	 * @param employee
	 * 従業員情報を変更する
	 */
	public void update(Employee employee) {
		String sql="update employees set dependets_count=:dependentsCount where id=:id";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		template.update(sql, param);
	}
}
