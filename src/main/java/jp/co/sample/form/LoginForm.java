package jp.co.sample.form;

/**
 * ログイン時に使用するフォーム
 * @author shoya
 *
 */
public class LoginForm {
	private String mailAddress;
	
	private String password;

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
