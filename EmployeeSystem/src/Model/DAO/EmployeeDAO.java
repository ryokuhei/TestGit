package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.EmployeeBean;

public class EmployeeDAO {

	public EmployeeDAO() {
	}

	/**
	 * 全従業員の情報を取得
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeBean> EmployeeDisplay() throws Exception {
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		String sql = "SELECT emp_code, l_name, f_name, l_kana_name, f_kana_name,sex, "
				+ "birth_day, section_name, emp_date "
				+ "FROM emp_sys_db.m_employee AS t1 LEFT JOIN emp_sys_db.m_section AS t2 "
				+ "ON t1.section_code = t2.section_code";

		ConnectionManager cm = ConnectionManager.getInstanse();

		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setCode(rs.getString("emp_code"));
				bean.setFName(rs.getString("f_name"));
				bean.setLName(rs.getString("l_name"));
				bean.setFKanaName(rs.getString("f_kana_name"));
				bean.setLKanaName(rs.getString("l_kana_name"));
				bean.setSex(rs.getInt("sex"));
				bean.setBirthDay(rs.getDate("birth_day"));
				bean.setSectionName(rs.getString("section_name"));
				bean.setEmpDate(rs.getDate("emp_date"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 従業員の情報をDBにインサート
	 * @param bean
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int EmployeeInsert(EmployeeBean bean) throws SQLException, Exception {
		ConnectionManager cm = ConnectionManager.getInstanse();
		int result = 0;
		String sql = "INSERT INTO emp_sys_db.m_employee(emp_code, l_name, f_name, l_kana_name, f_kana_name,"
				+ " sex, birth_day, section_code, emp_date)"
				+ " VALUES( ?, ?, ? ,?, ?, ?, ?, ?, ?)";

		// DB接続
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// ステークホルダー
			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getLName());
			pstmt.setString(3, bean.getFName());
			pstmt.setString(4, bean.getLKanaName());
			pstmt.setString(5, bean.getFKanaName());
			pstmt.setInt(6, bean.getSex());

			Date bDay = bean.getBirthDay();
			if(!(bDay == null)) {
				// SQLのDATE型へ変換
				java.sql.Date birthDay = new java.sql.Date(bDay.getTime());
				pstmt.setDate(7, birthDay);
			} else {
				pstmt.setDate(7, null);
			}

			pstmt.setString(8, bean.getSectionCode());

			Date eDate = bean.getEmpDate();
			if(!(eDate == null)) {
				// SQLのDATE型へ変換
				java.sql.Date empDate = new java.sql.Date(eDate.getTime());
				pstmt.setDate(9, empDate);
			} else {
				pstmt.setDate(9, null);
			}

			// 更新系SQL文を実行
			result = pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 従業員データをDBから削除
	 * @param delete
	 * @return
	 * @throws Exception
	 */
	public int EmployeeDelete(String delete) throws Exception {

		int result = 0;
		ConnectionManager cm = ConnectionManager.getInstanse();
		String sql = "DELETE FROM emp_sys_db.m_employee"
				+ " WHERE emp_code= ?";

		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, delete);

			result = pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * emp_codeの重複を確認
	 * @param empCode
	 * @return
	 * @throws Exception
	 */
	public boolean empCodeOverlapCheck(String empCode) throws Exception {
		int result = 0;
		boolean check = false;
		ResultSet rs;
		ConnectionManager cm = ConnectionManager.getInstanse();
		String sql = "SELECT count(emp_code) FROM emp_sys_db.m_employee WHERE emp_code = ?";
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// ステークホルダー
			pstmt.setString(1, empCode);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = Integer.parseInt(rs.getString("count(emp_code)"));
			}

			if(result == 0) {
				check = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * 全ての所属部署名を取得
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<String> getAllSectionName() throws SQLException, Exception {

		List<String> list = new ArrayList<String>();
		String sql = "SELECT section_name FROM emp_sys_db.m_section";
		ConnectionManager cm = ConnectionManager.getInstanse();
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("section_name"));
			}
		}

		return list;
	}

}
