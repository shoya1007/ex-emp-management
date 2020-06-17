package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

import java.util.Collections;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * 管理者関連機能の処理の制御を行うコントローラ
 * @author shoya
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private HttpSession Session;

	@Autowired
	private AdministratorService administratorService;
	
	
	
	/**
	 * setUpAdministratorFormメソッド
	 * @return インスタンス化したInsertAdministratorForm
	 * リクエストパラメータを格納したオブジェクトをリクエストスコープに格納するメソッド
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * setUpLoginFormメソッド
	 * @return インスタンス化したLoginFOrm
	 * リクエストパラメータを格納したオブジェクトをリクエストスコープに格納するメソッド
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	/**
	 * 
	 * @return 入力画面にフォワード 
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 
	 * @param form
	 * @return 登録後の画面にリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator =new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		return("redirect:/");
	}
	
	/**
	 * 
	 * @return ログイン画面にフォワード 
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		String mailAddress=form.getMailAddress();
		String password=form.getPassword();
		Administrator administrator = administratorService.login(mailAddress, password);
		if(administrator==null) {	
			model.addAttribute("err","エラー");
			model.addAttribute("errMessage","メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		}
		Session.setAttribute("administratorName", administrator.getName());
		
		
		return "forward:/employee/showList";
	}
	
	
	
	
}
