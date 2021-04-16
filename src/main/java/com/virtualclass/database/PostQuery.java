package com.virtualclass.database;

import java.sql.Connection;

import com.google.gson.Gson;
import com.virtualclass.model.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostQuery implements PostDAO {

	DBConnection connection;
	Connection conn;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	//List<Product> products;
	private Gson gson;
	
	public PostQuery(){
		connection = DBConnection.getDBConnection();
		conn = connection.connect;
		gson = new Gson();
	}

	@Override
	public String particularClass(String cid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select pid,ptitle,pcontent,uid,pdt from posts" 
					+ " where ptype=0 and cid=? order by pdt desc";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, cid);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Post> posts = new ArrayList<>();
		Post p;
		while(resultSet.next()) {
			p = new Post(
					resultSet.getString("pid"),
					"P",
					resultSet.getString("ptitle"),
					resultSet.getString("pcontent"),
					resultSet.getString("uid"),
					resultSet.getString("pdt")
					);
			posts.add(p);
		}
		return gson.toJson(posts);
	}
	
	public void addPost(String ptitle,String pcontent,String uid,String cid) throws SQLException {
	// TODO Auto-generated method stub
	
		String sql = "insert into posts(ptype,ptitle,pcontent,uid,cid) "+
						"values(0,?,?,?,?)";
		
	preparedStatement = conn
	          .prepareStatement(sql);
	
	preparedStatement.setString(1, ptitle);
	preparedStatement.setString(2, pcontent);
	preparedStatement.setString(3, uid);
	preparedStatement.setString(4, cid);
	
	
	preparedStatement.executeUpdate();
	System.out.println("Post Added!");
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