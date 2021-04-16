package com.virtualclass.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GeneralQuery implements GeneralDAO {

	DBConnection connection;
	Connection conn;
	private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	//private ResultSet resultSet = null;
	//List<Product> products;
	
	public GeneralQuery(){
		connection = DBConnection.getDBConnection();
		conn = connection.connect;
	}

	@Override
	public String loginUser(String uname, String password, int utype) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT UID  FROM users WHERE UNAME = ? and UPASS = ? AND UTYPE = ? limit 1";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uname);
		preparedStatement.setString(2, password);
		preparedStatement.setInt(3, utype);
		System.out.println("Query processed!");
		ResultSet resultSet = preparedStatement.executeQuery();
		int size = 0;
		resultSet.last();
		size = resultSet.getRow(); 
		return size>0?resultSet.getString("UID"):"SOS";
	}
	
//	@Override
//	public List<Product> getAllProducts() throws SQLException {
//		// TODO Auto-generated method stub
//		products = new ArrayList<Product>();
//		statement = conn.createStatement();
//		
//		resultSet = statement
//		          .executeQuery("SELECT * FROM products");
//		
//		Product p;
//		
//		while (resultSet.next()) {
//			p = new Product(
//					resultSet.getString("PID"),
//					resultSet.getString("PNAME"),
//					resultSet.getInt("PQUANTITY"),
//					resultSet.getFloat("PRICE_PER_UNIT")
//					);		     
//			products.add(p);
//		}
//		
//		return products;
//	}
//	
//	@Override
//	public void addProduct(Product product) throws SQLException {
//		// TODO Auto-generated method stub
//		
//		preparedStatement = conn
//		          .prepareStatement("INSERT INTO  products(PNAME,PQUANTITY,PRICE_PER_UNIT) values ( ?, ?, ?)");
//		
//		System.out.println("Product Name : "+product.getPname());
//		
//		preparedStatement.setString(1, product.getPname());
//		preparedStatement.setInt(2, product.getPquant());
//		preparedStatement.setFloat(3, product.getPrice_per_unit());
//		
//		preparedStatement.executeUpdate();
//		System.out.println("Product Added!");
//	}
//
//	@Override
//	public void updateProduct(Product product) throws SQLException {
//		// TODO Auto-generated method stub
//		preparedStatement = conn
//			      .prepareStatement("UPDATE products SET PNAME = ?,PQUANTITY = ?,PRICE_PER_UNIT = ? WHERE PID = ?");
//			      preparedStatement.setString(1, product.getPname());
//			      preparedStatement.setInt(2, product.getPquant());
//			      preparedStatement.setFloat(3, product.getPrice_per_unit());
//			      preparedStatement.setString(4, product.getPid());
//			      preparedStatement.executeUpdate();
//	}
//
//	@Override
//	public void deleteProduct(String pid) throws SQLException {
//		// TODO Auto-generated method stub
//		preparedStatement = conn
//			      .prepareStatement("DELETE FROM products where PID = ?");
//			      preparedStatement.setString(1, pid);
//			      preparedStatement.executeUpdate();
//	}
//
//	@Override
//	public boolean pidExist(String pid) throws SQLException {
//		// TODO Auto-generated method stub
//		resultSet = statement
//		          .executeQuery("SELECT * FROM products WHERE PID = '"+pid+"'");
//		resultSet.last();
//		return resultSet.getRow()>0?true:false;
//	}

}