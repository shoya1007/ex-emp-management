package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.IntegerDeserializer;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * showListメソッド
	 * @return findAllメソッドからの戻り値
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	
	/**
	 * 従業員情報を取得するshowDetailメソッド
	 * @param id
	 * @return loadメソッドで取得した従業員情報
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
	
	/**
	 * 従業員情報を更新するupdateメソッド
	 * @param employee
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
	public Employee load(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}

}
