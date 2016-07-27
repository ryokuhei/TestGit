package model.entity;

import java.io.Serializable;

public class SectionBean implements Serializable {

	private int sectionCode;		// 部署コード
	private String sectionName;		// 部署名


	// 以下、getter setter
	public int getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(int sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}
