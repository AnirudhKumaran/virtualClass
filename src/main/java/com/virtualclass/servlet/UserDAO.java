package com.virtualclass.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public boolean checkLogin(String uname, String password,int utype) throws SQLException,
    ClassNotFoundException {
	String jdbcURL = "jdbc:mysql://localhost:3306/virtualclass";
	String dbUser = "root";
	String dbPassword = "9187";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	String sql = "SELECT UID,UPASS  FROM users WHERE UNAME = ? and UPASS = ? AND UTYPE = ? limit 1";
	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setString(1, uname);
	statement.setString(2, password);
	statement.setInt(3, utype);
	System.out.println("Query processed!");
	ResultSet result = statement.executeQuery();
	result.next();
	int count = result.getInt("rowcount");
	
//	User user = null;
//	
//	if (result.next()) {
//	    user = new User();
//	    user.setFullname(result.getString("fullname"));
//	    user.setEmail(email);
//	}
	
	connection.close();
	
	return count>0?true:false;
	}
	
}
