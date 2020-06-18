package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 管理者登録時に使用するフォーム
 * 
 * @author shoya
 *
 */
public class InsertAdministratorForm {
	@NotBlank(message = "名前は必須です")
	private String name;
	@NotBlank(message = "メールアドレスは必須です")
	private String mailAddress;
	@NotBlank(message = "パスワードは必須です")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return super.toString();
	}

}
