package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// DBログイン情報
	private static final String URL = "jdbc:mysql://localhost:3306/emp_sys_db";
	private static final String USER = "empuser";
	private static final String PASSWORD = "emppass";

	// 自分自身をインスタンス化
	private static ConnectionManager instanse = null;
//	private static ConnectionManager instanse = new ConnectionManager();
	private Connection connection = null;

	// コンストラクタ
	private ConnectionManager() {
	}

	// インスタンス取得
	public static synchronized ConnectionManager getInstanse() {
		if(instanse == null) {
			instanse = new ConnectionManager();
		}

		return instanse;
	}

	/**
	 * DBの接続
	 * @return
	 */
	public Connection getConnection() throws Exception {

		String dry = "com.mysql.jdbc.Driver";
		try {
			Class.forName(dry);
		} catch(ClassNotFoundException e) {
			System.out.println("ドライバがありません" + e.getMessage());
		}

		try {
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		} catch(Exception e) {
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}

	/**
	 * DBの切断
	 */
	public void closeConnection() {

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}
}
