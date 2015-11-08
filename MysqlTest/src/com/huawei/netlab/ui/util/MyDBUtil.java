package com.huawei.netlab.ui.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDBUtil {
	private static String driver = null;
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	private static ThreadLocal<Connection> tong = new ThreadLocal<Connection>();

	static {
		try {
			Properties p = new Properties();
		
			InputStream inStream = MyDBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		
			p.load(inStream);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			userName = p.getProperty("userName");
			password = p.getProperty("password");
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnetion() {
	Connection conn = null;
	try {
		conn=tong.get();
		if(conn==null){
			conn=DriverManager.getConnection(url,userName,password);
			tong.set(conn);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} 
		return conn;
	}


	public static void closeConnection(){
		Connection conn = tong.get();
		if(conn!=null){
			try {
				conn.close();
				tong.set(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

