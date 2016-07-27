package model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import model.dao.EmployeeDAO;
import model.dao.SectionDAO;

public class EmployeeBean implements Serializable {

	/* フィールド定義 */
	private String	employeeNumber;		// 社員番号
	private String	firstName;			// 苗字
	private String	lastName;			// 名前
	private String	phoneticFirstName;	// 苗字の送り仮名
	private String	phoneticLastName;	// 名前の送り仮名
	private int 	sex;				// 性別
	private Date 	birthDay;			// 誕生日
	private int 	sectionCode;		// 部署コード
	private String	sectionName;		// 部署名
	private Date 	hireDate;			// 入社日

	// カタカナチェック用
	public static final String MATCH_KATAKANA = "^[\\u30A0-\\u30FF]+$";
	// error用
	List<String> error = new ArrayList<String>();

	/**
	 * 入力した値をbeanに格納
	 * @param request
	 * @return
	 */
	public EmployeeBean setAll(HttpServletRequest request) {
		EmployeeBean bean = new EmployeeBean();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		SectionDAO sectionDAO = new SectionDAO();

		this.employeeNumber = request.getParameter("EMPLOYEE_NUMBER");
		if(StringUtils.isBlank(this.employeeNumber)) {
			error.add("従業員コードが入力されていません");
			//			this.employeeNumber = null;
		}else if(!(employeeDAO.empCodeOverlapCheck(this.employeeNumber))) {
			error.add("従業員コードが重複しています");
		}
		this.firstName = request.getParameter("FIRST_NAME");
		if(StringUtils.isBlank(this.firstName)) {
			error.add("従業員の名前が入力されていません");
			//			this.firstName = null;
		}
		this.lastName = request.getParameter("LAST_NAME");
		if(StringUtils.isBlank(this.lastName)) {
			error.add("従業員の苗字が入力されていません");
			//			this.lastName = null;
		}
		this.phoneticFirstName = request.getParameter("PHONETIC_FIRST_NAME");
		if(StringUtils.isBlank(this.phoneticFirstName)) {
			this.phoneticFirstName = null;
		}else if(!(this.phoneticFirstName.matches(MATCH_KATAKANA)) ) {
			error.add("名前（カナ）がカタカナで入力されていません");
		}
		this.phoneticLastName = request.getParameter("PHONETIC_LAST_NAME");
		if(StringUtils.isBlank(this.phoneticLastName)) {
			this.phoneticLastName = null;
		}else if(!(this.phoneticLastName.matches(MATCH_KATAKANA)) ) {
			error.add("苗字（カナ）がカタカナで入力されていません");
		}
		try {
			this.sex = Integer.parseInt(request.getParameter("SEX"));
		} catch(NumberFormatException e) {
			this.sex = -1;
			e.printStackTrace();
		}
		String date = request.getParameter("BIRTH_DAY");
		try {
			this.birthDay = Date.valueOf(date.replace("/", "-"));
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			this.birthDay = null;
		}
		try {
			this.sectionCode = Integer.parseInt(request.getParameter("SECTION_CODE"));
			this.sectionName = sectionDAO.getSectionName(this.sectionCode);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			this.sectionCode = -1;
		} catch (SQLException e) {
			e.printStackTrace();
			error.add("入力された部署が存在しません");
		}
		date = request.getParameter("HIRE_DATE");
		try {
			this.hireDate = Date.valueOf(date.replace("/", "-"));
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			error.add("入社日が正しいフォーマットで入力されていません");
			this.hireDate = null;
		}
		return bean;
	}

	/**
	 * 入力した値をbeanに格納(overLord)
	 * @param request
	 * @param overlapOFF	// 従業員の重複確認をしない
	 * @return
	 */
	public EmployeeBean setAll(HttpServletRequest request, int overlapOFF) {
		EmployeeBean bean = new EmployeeBean();
		SectionDAO sectionDAO = new SectionDAO();

		this.employeeNumber = request.getParameter("EMPLOYEE_NUMBER");
		if(StringUtils.isBlank(this.employeeNumber)) {
			error.add("従業員コードが入力されていません");
			//			this.employeeNumber = "未入力";
		}
		this.firstName = request.getParameter("FIRST_NAME");
		if(StringUtils.isBlank(this.firstName)) {
			error.add("従業員の名前が入力されていません");
			//			this.firstName = null;
		}
		this.lastName = request.getParameter("LAST_NAME");
		if(StringUtils.isBlank(this.lastName)) {
			error.add("従業員の苗字が入力されていません");
			//			this.lastName = null;
		}
		this.phoneticFirstName = request.getParameter("PHONETIC_FIRST_NAME");
		if(StringUtils.isBlank(this.phoneticFirstName)) {
			this.phoneticFirstName = null;
		}else if(!(this.phoneticFirstName.matches(MATCH_KATAKANA))) {
			error.add("名前（カナ）がカタカナで入力されていません");
		}
		this.phoneticLastName = request.getParameter("PHONETIC_LAST_NAME");
		if(StringUtils.isBlank(this.phoneticLastName)) {
			this.phoneticLastName = null;
		}else if(!(this.phoneticLastName.matches(MATCH_KATAKANA))) {
			error.add("苗字（カナ）がカタカナで入力されていません");
		}
		try {
			this.sex = Integer.parseInt(request.getParameter("SEX"));
		} catch(NumberFormatException e) {
			this.sex = -1;
			e.printStackTrace();
		}
		String date = request.getParameter("BIRTH_DAY");
		try {
			this.birthDay = Date.valueOf(date.replace("/", "-"));
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			this.birthDay = null;
		}
		try {
			this.sectionCode = Integer.parseInt(request.getParameter("SECTION_CODE"));
			this.sectionName = sectionDAO.getSectionName(this.sectionCode);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			this.sectionCode = -1;
		} catch (SQLException e) {
			e.printStackTrace();
			error.add("入力された部署が存在しません");
		}
		date = request.getParameter("HIRE_DATE");
		try {
			this.hireDate = Date.valueOf(date.replace("/", "-"));
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			error.add("入社日が正しいフォーマットで入力されていません");
			this.hireDate = null;
		}
		return bean;
	}

	// 以下、getter setter
	public List<String> getError() {
		return error;
	}


	public void setError(List<String> error) {
		this.error = error;
	}


	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneticFirstName() {
		return phoneticFirstName;
	}
	public void setPhoneticFirstName(String phoneticFirstName) {
		this.phoneticFirstName = phoneticFirstName;
	}
	public String getPhoneticLastName() {
		return phoneticLastName;
	}
	public void setPhoneticLastName(String phoneticLastName) {
		this.phoneticLastName = phoneticLastName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public int getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(int sectionCode) {
		this.sectionCode = sectionCode;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
}
