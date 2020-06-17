package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を行うコントローラ
 * @author shoya
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	
	/**
	 * 
	 * @return インスタンス化したInsertAdministratorForm
	 * リクエストパラメータを格納したオブジェクトをリクエストパラメータに格納するメソッド
	 */
	public InsertAdministratorForm setUpAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator =new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		return("/");
	}
	
	
}
