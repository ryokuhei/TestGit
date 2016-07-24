package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.SectionBean;

public class SectionDAO {

	public List<SectionBean> getSectionList() {

		/**
		 * 部署リストを取得
		 */
		List<SectionBean> list = new ArrayList<SectionBean>();

		ConnectionManager cm = ConnectionManager.getInstance();
		String sql = "SELECT * FROM employee_management_db.section ORDER BY SECTION_CODE ASC";

		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)
				) {
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				SectionBean bean = new SectionBean();
				bean.setSectionCode(res.getInt("SECTION_CODE"));
				bean.setSectionName(res.getString("SECTION_NAME"));
				list.add(bean);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 部署名を取得
	 * @param sectionCode
	 * @return
	 * @throws SQLException
	 */
	public String getSectionName(int sectionCode) throws SQLException {

		String sql = "SELECT SECTION_NAME FROM employee_management_db.section WHERE SECTION_CODE = ?";

		String sectionName = null;
		ConnectionManager cm = ConnectionManager.getInstance();

		try(Connection connection = cm.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, sectionCode);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				sectionName = res.getString("SECTION_NAME");
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sectionName;
	}
}