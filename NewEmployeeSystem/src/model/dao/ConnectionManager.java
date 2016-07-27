package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// コンストラクタ
	private ConnectionManager() {
	}

	// 接続用　URL,USER,PASSWORD
	private final String URL = "jdbc:mysql://localhost:3306/employee_management_db";
	private final String USER = "user";
	private final String PASSWORD = "password";

	static private ConnectionManager instance = null;
	private Connection connection = null;

	/**
	 * インスタンス取得（シングルトン）メソッド
	 * @return	自クラスのインスタンス
	 */
	public static synchronized ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	/**
	 * DBに接続するメソッド
	 * @return	接続コネクション
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			System.out.println("ドラバーがありません" + e.getMessage());

		}
		try {
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}

	/**
	 * DBから切断するメソッド
	 */
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}
}
