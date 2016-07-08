package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.UserBean;

public class UserDAO {


	public UserBean loginCheck(String id, String password) throws Exception {
		UserBean bean = new UserBean();
		// MySQL分
		String sql = "SELECT * FROM emp_sys_db.m_user WHERE user_id = ? AND password = ?";

		ConnectionManager cm = ConnectionManager.getInstanse();

		// DBのコネクション取得
		try (Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// ステークホルダー
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password"));
				bean.setUpdateDate(rs.getTimestamp("update_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
