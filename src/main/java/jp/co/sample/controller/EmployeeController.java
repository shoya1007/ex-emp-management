package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.IntegerDeserializer;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員関連機能の処理の制御を行うコントローラ
 * 
 * @author shoya
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@ModelAttribute
	public UpdateEmployeeForm setUpEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員一覧を出力するshowListメソッド
	 * 
	 * @param model
	 * @return 従業員一覧画面にフォワード
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * 従業員詳細を出力するshowDetailメソッド
	 * 
	 * @param id
	 * @param model
	 * @return 従業員詳細画面にフォワード
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model,UpdateEmployeeForm form) {
		int intId = Integer.parseInt(id);
		Employee employee=employeeService.showDetail(intId);
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/**
	 * 扶養人数を更新するupdateメソッド
	 * @param form
	 * @return 従業員一覧にリダイレクト
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
}
