package jp.co.sample.form;
/**
 * UpdateEmployeeFormクラス
 * @author shoya
 * 従業員更新時に使用するフォーム
 */
public class UpdateEmployeeForm {
	private String id;
	
	private String dependentsCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return super.toString();
	}
	
	
}
