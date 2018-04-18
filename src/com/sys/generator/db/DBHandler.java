package com.sys.generator.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sys.generator.consts.GeneratorConsts;

/**
 * 创建数据库连接
 * 
 * @author
 * @date
 */
public class DBHandler {

	private static Connection conn;

	public static final Connection createConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@" + GeneratorConsts.DB_HOST
					+ ":" + GeneratorConsts.DB_PORT + "/" + GeneratorConsts.DB_NAME,
					GeneratorConsts.DB_USER, GeneratorConsts.DB_PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static final void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
