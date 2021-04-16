package com.virtualclass.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.virtualclass.model.Video;

public class VideoQuery implements VideoDAO {

	DBConnection connection;
	Connection conn;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	//List<Product> products;
	
	Gson gson;
	
	public VideoQuery(){
		connection = DBConnection.getDBConnection();
		conn = connection.connect;
		gson = new Gson();
	}

	public void uploadVideo(String vtitle,String vcont,String fname,String uid,String cid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO videos(VTITLE,VCONT,VPATH,UID,CID,VSTAT) VALUES (?,?,?,?,?,0)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, vtitle);
		preparedStatement.setString(2, vcont);
		preparedStatement.setString(3, fname);
		preparedStatement.setString(4, uid);
		preparedStatement.setString(5, cid);
		//System.out.println("Query processed!");
		preparedStatement.executeUpdate();
		System.out.println("Video Uploaded!");
	}
	
	@Override
	public String getAllVideos(String cid) throws SQLException {
		// TODO Auto-generated method stub
		List<Video> videos = new ArrayList<Video>();
		statement = conn.createStatement();
		
		resultSet = statement
		          .executeQuery("SELECT VID,VTITLE,VCONT,VPATH,VSTAT,VDT as pdt,UID from videos where VSTAT=1 and CID = '"+cid+"' order by vdt desc;");
		
		Video v;
		
		while (resultSet.next()) {
			v = new Video(
					resultSet.getString("VID"),
					"V",
					resultSet.getString("VTITLE"),
					resultSet.getString("VCONT"),
					resultSet.getString("VPATH"),
					resultSet.getInt("VSTAT"),
					resultSet.getString("UID"),
					resultSet.getString("pdT")
					);		     
			videos.add(v);
		}
		
		return gson.toJson(videos);
	}
	
	public List<Video> getAllVideos() throws SQLException {
		// TODO Auto-generated method stub
		List<Video> videos = new ArrayList<Video>();
		statement = conn.createStatement();
		
		resultSet = statement
		          .executeQuery("SELECT v.VID,v.VTITLE,v.VCONT,v.VPATH,(SELECT UNAME from users WHERE UID=v.UID) AS UID,v.VSTAT,v.VDT from videos v order by vdt desc;");
		
		Video v;
		
		while (resultSet.next()) {
			v = new Video(
					resultSet.getString("VID"),
					resultSet.getString("VTITLE"),
					resultSet.getString("VCONT"),
					resultSet.getString("VPATH"),
					resultSet.getString("UID"),
					resultSet.getInt("VSTAT"),
					resultSet.getString("VDT")
					);		     
			videos.add(v);
		}
		
		return videos;
	}
	
	@Override
	public void updateVideo(String vid,int vstat) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement = conn
			      .prepareStatement(" update videos set vstat = ? where vid=?");
		preparedStatement.setInt(1, vstat);
					preparedStatement.setString(2, vid);
			      preparedStatement.executeUpdate();
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