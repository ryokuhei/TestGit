package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.EmployeeBean;

public class EmployeeDAO {

	/**
	 * 従業員一覧情報を取得するメソッド
	 * @return	従業員一覧リスト
	 */
	public List<EmployeeBean> employeeList() {
		String sql = "SELECT * FROM employee_management_db.employee"
				+ " INNER JOIN employee_management_db.section"
				+ " ON employee_management_db.employee.SECTION_CODE"
				+ " = employee_management_db.section.SECTION_CODE ORDER BY EMPLOYEE_NUMBER ASC";

		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		ResultSet res = null;
		ConnectionManager cm = ConnectionManager.getInstance();
		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)
				) {

			res = pstmt.executeQuery();

			while(res.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setEmployeeNumber(res.getString("EMPLOYEE_NUMBER"));
				bean.setFirstName(res.getString("FIRST_NAME"));
				bean.setLastName(res.getString("LAST_NAME"));
				bean.setPhoneticFirstName(res.getString("PHONETIC_FIRST_NAME"));
				bean.setPhoneticLastName(res.getString("PHONETIC_LAST_NAME"));
				bean.setSex(res.getInt("SEX"));
				bean.setBirthDay(res.getDate("BIRTH_DAY"));
				bean.setSectionCode(res.getInt("SECTION_CODE"));
				bean.setSectionName(res.getString("SECTION_NAME"));
				bean.setHireDate(res.getDate("HIRE_DATE"));
				list.add(bean);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 従業員情報を削除するメソッド
	 * @param code	削除する従業員番号
	 * @return		削除結果
	 */
	public int employeeDelete(String code) {

		String sql = "DELETE FROM employee_management_db.employee WHERE EMPLOYEE_NUMBER = ?";
		int result = 0;

		ConnectionManager cm = ConnectionManager.getInstance();

		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, code);

			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 従業員情報を登録するメソッド
	 * @param bean	従業員情報
	 * @return		登録結果
	 */
	public int employeeRegistration(EmployeeBean bean) {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO employee_management_db.employee (");
		int result = 0;
		int cnt = 0;
		if(bean.getEmployeeNumber() != null) {
			sql.append("EMPLOYEE_NUMBER, ");
			cnt++;
		}
		if(bean.getLastName() != null) {
			sql.append("LAST_NAME, ");
			cnt++;
		}
		if(bean.getFirstName() != null) {
			sql.append("FIRST_NAME, ");
			cnt++;
		}
		if(bean.getPhoneticLastName() != null) {
			sql.append("PHONETIC_LAST_NAME, ");
			cnt++;
		}
		if(bean.getPhoneticFirstName() != null) {
			sql.append("PHONETIC_FIRST_NAME, ");
			cnt++;
		}
		if(bean.getSex() != -999) {
			sql.append("SEX, ");
			cnt++;
		}
		if(bean.getBirthDay() != null) {
			sql.append("BIRTH_DAY, ");
			cnt++;
		}
		if(bean.getSectionCode() != -999) {
			sql.append("SECTION_CODE, ");
			cnt++;
		}
		if(bean.getHireDate() != null) {
			sql.append("HIRE_DATE, ");
			cnt++;
		}
		sql.delete(sql.length() -2, sql.length());
		sql.append(") VALUES (");
		for(int i = 0; i < cnt; i++) {
			sql.append("?, ");
		}
		sql.delete(sql.length() -2, sql.length());
		sql.append(")");

		ConnectionManager cm = ConnectionManager.getInstance();

		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(new String(sql))) {
			cnt = 1;
			// 従業員番号
			if(bean.getEmployeeNumber() != null) {
				pstmt.setString(cnt, bean.getEmployeeNumber());
				cnt++;
			}
			// 苗字
			if(bean.getLastName() != null) {
				pstmt.setString(cnt, bean.getLastName());
				cnt++;
			}
			// 名前
			if(bean.getFirstName() != null) {

				pstmt.setString(cnt, bean.getFirstName());
				cnt++;
			}
			// 苗字（カナ）
			if(bean.getPhoneticLastName() != null) {
				pstmt.setString(cnt, bean.getPhoneticLastName());
				cnt++;
			}
			// 名前 (カナ）
			if(bean.getPhoneticFirstName() != null) {
				pstmt.setString(cnt, bean.getPhoneticFirstName());
				cnt++;
			}
			// 性別
			if(bean.getSex() != -999) {
				pstmt.setInt(cnt, bean.getSex());
				cnt++;
			}
			// 生年月日
			if(bean.getBirthDay() != null) {
				pstmt.setDate(cnt, bean.getBirthDay());
				cnt++;
			}
			// 部署コード
			if(bean.getSectionCode() != -999) {
				pstmt.setInt(cnt, bean.getSectionCode());
				cnt++;
			}
			// 入社日
			if(bean.getHireDate() != null) {
				pstmt.setDate(cnt, bean.getHireDate());
			}

			result = pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 従業員を検索するメソッド
	 * @param code		検索する従業員番号
	 * @return			検索がヒットした従業員情報
	 */
	public EmployeeBean employeeSearch(String code) {

		String sql = "SELECT * FROM employee_management_db.employee WHERE EMPLOYEE_NUMBER = ?";

		EmployeeBean bean = new EmployeeBean();

		ConnectionManager cm = ConnectionManager.getInstance();
		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				) {
			pstmt.setString(1, code);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				bean.setEmployeeNumber(res.getString("EMPLOYEE_NUMBER"));
				bean.setLastName(res.getString("LAST_NAME"));
				bean.setFirstName(res.getString("FIRST_NAME"));
				bean.setPhoneticLastName(res.getString("PHONETIC_LAST_NAME"));
				bean.setPhoneticFirstName(res.getString("PHONETIC_FIRST_NAME"));
				bean.setSex(res.getInt("SEX"));
				bean.setBirthDay(res.getDate("BIRTH_DAY"));
				bean.setSectionCode(res.getInt("SECTION_CODE"));
				bean.setHireDate(res.getDate("HIRE_DATE"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return bean;

	}

	/**
	 * 従業員情報を編集するメソッド
	 * @param oldCode	編集する従業員番号
	 * @param bean		編集した従業員情報
	 * @return			編集結果
	 */
	public int employeeEdit(String oldCode, EmployeeBean bean) {

		String sql = "UPDATE employee_management_db.employee SET"
				+ " EMPLOYEE_NUMBER = ?,"
				+ " LAST_NAME = ?,"
				+ " FIRST_NAME = ?,"
				+ " PHONETIC_LAST_NAME = ?,"
				+ " PHONETIC_FIRST_NAME = ?,"
				+ " SEX = ?,"
				+ " BIRTH_DAY = ?,"
				+ " SECTION_CODE = ?,"
				+ " HIRE_DATE = ?"
				+ " WHERE EMPLOYEE_NUMBER = ?";
		int result = 0;

		ConnectionManager cm = ConnectionManager.getInstance();
		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, bean.getEmployeeNumber());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getPhoneticLastName());
			pstmt.setString(5, bean.getPhoneticFirstName());
			pstmt.setInt(6, bean.getSex());
			pstmt.setDate(7, bean.getBirthDay());
			pstmt.setInt(8, bean.getSectionCode());
			pstmt.setDate(9, bean.getHireDate());
			pstmt.setString(10, oldCode);

			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 従業員が重複しているか確認するメソッド
	 * @param employeeNumber	確認する従業員番号
	 * @return					重複結果
	 */
	public boolean empCodeOverlapCheck(String employeeNumber) {

		String sql = "SELECT EMPLOYEE_NUMBER FROM employee_management_db.employee WHERE EMPLOYEE_NUMBER = ?";
		ConnectionManager cm = ConnectionManager.getInstance();
		boolean OverlapChec = true;

		try(Connection connection = cm.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, employeeNumber);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				OverlapChec = false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return OverlapChec;
	}
}
