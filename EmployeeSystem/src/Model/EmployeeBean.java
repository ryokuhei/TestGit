package Model;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Model.DAO.ConversionDAO;
import Model.DAO.EmployeeDAO;

public class EmployeeBean implements Serializable {

	// フィールド定義
	private String code;
	private String lName;
	private String fName;
	private String lKanaName;
	private String fKanaName;
	private int sex;
	private Date birthDay;
	private String sectionCode;
	private String sectionName;
	private Date empDate;
	// カタカナチェック用
	public static final String MATCH_KATAKANA = "^[\\u30A0-\\u30FF]+$";
	// error用
	List<String> error = new ArrayList<String>();


	public EmployeeBean() {
	}

	// 以下、Getter Setter

	public String getCode() {
		return code;
	}
	public List<String> getError() {
		return error;
	}

	public void setCode(String code) {
		this.code = code;
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
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Date getEmpDate() {
		return empDate;
	}
	public void setEmpDate(Date empDate) {
		this.empDate = empDate;
	}

	public String getFKanaName() {
		return fKanaName;
	}

	public void setFKanaName(String fKanaName) {
		this.fKanaName = fKanaName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLKanaName() {
		return lKanaName;
	}

	public void setLKanaName(String lKanaName) {
		this.lKanaName = lKanaName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setlKanaName(String lKanaName) {
		this.lKanaName = lKanaName;
	}

	public void setfKanaName(String fKanaName) {
		this.fKanaName = fKanaName;
	}

	public String getSexName() {
		String sexName = null;
		if(sex == 1) {
			sexName = "男";
		} else if(sex == 2) {
			sexName = "女";
		}
		return sexName;
	}

	public void setAll(HttpServletRequest request) throws SQLException, Exception {

		// DAO生成
		ConversionDAO cDao= new ConversionDAO();
		EmployeeDAO dao = new EmployeeDAO();

		// 	パラメータ取得
		String code = request.getParameter("CODE");
		String lName = request.getParameter("L_NAME");
		String fName = request.getParameter("F_NAME");
		String lKanaName = request.getParameter("L_KANA_NAME");
		String fKanaName = request.getParameter("F_KANA_NAME");
		String sectionName = request.getParameter("SECTION_NAME");
		String sectionCode = null;
		if(!(sectionName == null || sectionName.isEmpty())) {
			sectionCode = cDao.sectionNameToCode(sectionName);
		}
		String sexName = request.getParameter("SEX");
		int sexCode = 0;
		if(!(sexName == null || sexName.isEmpty())) {
			sexCode = cDao.sexNameToCode(sexName);
		}

		String sBYear = request.getParameter("B_YEAR");
		String sBMonth = request.getParameter("B_MONTH");
		String sBDate = request.getParameter("B_DATE");
		String sEYear = request.getParameter("E_YEAR");
		String sEMonth = request.getParameter("E_MONTH");
		String sEDate = request.getParameter("E_DATE");

		int bYear = 0;
		int bMonth = 0;
		int bDate = 0;
		int eYear = 0;
		int eMonth = 0;
		int eDate = 0;

		try {
			bYear = Integer.parseInt(sBYear);
			bMonth = Integer.parseInt(sBMonth);
			bDate = Integer.parseInt(sBDate);
			eYear = Integer.parseInt(sEYear);
			eMonth = Integer.parseInt(sEMonth);
			eDate = Integer.parseInt(sEDate);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}

		// 日付のフォーマット登録
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		// 従業員コード
		if(!(code == null || code.isEmpty())) {
			if(code.getBytes().length > 4 ) {
				error.add("従業員コードが4Byte以上になっています。");
			}
			else if(dao.empCodeOverlapCheck(code) == false) {
				error.add("従業員コードが重複しています。");
			} else {
				this.code = code;
			}
		}
		// 名前
		if(!(fName == null || fName.isEmpty())) {
			if(fName.getBytes().length > 16) {
				error.add("名前が16Byte以上になっています。");
			} else {
				this.fName = fName;
			}
		}
		// 苗字
		if(!(lName == null || lName.isEmpty())) {
			if(lName.getBytes().length > 16) {
				error.add("名前が16Byte以上になっています。");
			} else {
				this.lName = lName;
			}
		}
		// 名前（カナ）
		if(!(fKanaName == null || fKanaName.isEmpty())) {
			if(!(matchKatakana(fKanaName))) {
				error.add("氏（フリガナ）がカタカナではありません。");
			} else {
				this.fKanaName = fKanaName;

			}
		}
		// 苗字（カナ）
		if(!(lKanaName == null || lKanaName.isEmpty())) {
			if(!(matchKatakana(lKanaName))) {
				error.add("名（フリガナ）がカタカナではありません。");
			} else {
				this.lKanaName = lKanaName;
			}
		}
		// 性別
		if(!(sexCode == 0)) {
			this.sex = sexCode;
		}
		// 生年月日
		if(bYear != 0 ||  bMonth != 0 || bDate != 0 ) {
			if(bMonth >= 1 && bMonth <= 12 && bDate >= 1 && bDate <= this.maxDate(bYear, bMonth)) {
				Date birthDate = df.parse(bYear + "/" + bMonth + "/" + bDate);
				this.birthDay = birthDate;
			} else {
				error.add("生年月日の入力が正しくありません");
			}
		}
		// 所属部署
		if(!(sectionCode == null || sectionCode.isEmpty())) {
			this.sectionCode = sectionCode;
		}
		// 入社日
		if(eYear != 0 ||  eMonth != 0 || eDate != 0 ) {
			if(eMonth >= 1 && eMonth <= 12 && eDate >= 1 && eDate <= this.maxDate(eYear, eMonth)) {
				Date empDate = df.parse(eYear + "/" + eMonth + "/" + eDate);
				this.empDate = empDate;
			}else {
				error.add("入社日の入力が正しくありません");
			}
		}
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public boolean matchKatakana(String katakana) {
		boolean check = false;
		if(katakana.matches(MATCH_KATAKANA)) {
			check = true;
		}
		return check;
	}

	public int maxDate(int year, int month) {
		int date = 31;

		if(year % 4 == 0 && month == 2) {
			date = 29;
		} else if(month % 2 == 0) {
			date = 30;
		}

		return date;
	}
}

