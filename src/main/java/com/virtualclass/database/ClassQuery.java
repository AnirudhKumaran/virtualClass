package com.virtualclass.database;

import java.sql.Connection;
import com.virtualclass.model.Classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassQuery implements ClassDAO {

	DBConnection connection;
	Connection conn;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	//List<Product> products;
	
	public ClassQuery(){
		connection = DBConnection.getDBConnection();
		conn = connection.connect;
	}

	@Override
	public List particularStudent(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT CID,CNAME FROM classes where CID in "
					+ "(SELECT CID from staff_class_student where UID=?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uid);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Classes> classes = new ArrayList<>();
		Classes c;
		while(resultSet.next()) {
			c = new Classes(resultSet.getString("CID"),resultSet.getString("CNAME"));
			classes.add(c);
		}
		return classes;
	}
	
	public String getClassDescription(String cid) throws SQLException {
		
		String sql = "SELECT CDESC FROM classes where CID = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, cid);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getString("CDESC");
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