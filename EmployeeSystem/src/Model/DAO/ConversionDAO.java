package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversionDAO {

	/**
	 * 指定したコードの所属部署名を取得
	 * @param sectionCode
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String sectionCodeToName(String sectionCode) throws SQLException, Exception {

		String sectionName = null;
		ConnectionManager cm = ConnectionManager.getInstanse();
		String sql = "SELECT section_name FROM emp_sys_db.m_section WHERE section_code = ?";

		// DB接続
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// ステークホルダー
			pstmt.setString(1 ,sectionCode);
			// SQL実行
			ResultSet rs = pstmt.executeQuery();
			// 結果を抽出
			while(rs.next()) {
				sectionName = rs.getString("section_name");
			}
		}
		return sectionName;
	}


	/**
	 * 指定した所属部署名のコードを取得
	 * @param sectionName
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String sectionNameToCode(String sectionName) throws SQLException, Exception {

		String sectionCode = null;
		ConnectionManager cm = ConnectionManager.getInstanse();
		String sql = "SELECT section_code FROM emp_sys_db.m_section WHERE section_name = ?";

		// DB接続
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// 	プレースホルダ
			pstmt.setString(1 ,sectionName);
			// SQL実行
			ResultSet rs = pstmt.executeQuery();
			// 結果を抽出
			while(rs.next()) {
				sectionCode = rs.getString("section_code");
			}
		}
		return sectionCode;
	}

	/**
	 * 指定した性別コードの性別を取得
	 * @param sexCode
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String sexCodeToName(int sexCode) throws SQLException, Exception {

		String sexName = null;

		if(sexCode == 1) {
			sexName = "男性";
		} else if(sexCode == 2) {
			sexName = "女性";
		} else {
			sexName = "不明";
		}
		return sexName;
	}


	/**
	 * 指定した性別の性別コードを取得
	 * @param sexName
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int sexNameToCode(String sexName) throws SQLException, Exception {

		int sexCode = 0;

		if("男性".equals(sexName)) {
			sexCode = 1;
		} else if("女性".equals(sexName)) {
			sexCode = 2;
		}

		return sexCode;
	}

}
