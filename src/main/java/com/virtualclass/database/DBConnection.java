package com.virtualclass.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {

	private static DBConnection single_instance = null;
	
	final private String host = "localhost:3306";
	final private String user = "root";
	final private String passwd = "9187";
	final private String database = "virtualclass";
	
	public Connection connect = null;
	
	private DBConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DB Connection Establishing...");
			connect = DriverManager
			          .getConnection("jdbc:mysql://" + host + "/"+database+"?"
			                  + "user=" + user + "&password=" + passwd );
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static DBConnection getDBConnection() {
		
		System.out.println("getconnection was called");
		if(single_instance==null)
			single_instance = new DBConnection();
	
		return single_instance;
	}
}
