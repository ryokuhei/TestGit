package Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserBean implements Serializable {

	// フィールド定義
	private String userId;
	private String password;
	private Timestamp updateDate;


	// 以下、Getter Setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
