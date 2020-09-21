package com.cognixia.jump.library.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static Connection conn = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=CST";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root"; 
	
	private static void  makeConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not make connection");
		}
		
	}
	
	public static Connection getConnection() {
		if(conn == null) {
			makeConnection();
		}
		return conn;
	}
}