package com.virtualclass.database;

import java.sql.Connection;

import com.google.gson.Gson;
import com.virtualclass.model.Doubt;
import com.virtualclass.model.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DoubtsQuery implements PostDAO {

	DBConnection connection;
	Connection conn;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	//List<Product> products;
	private Gson gson;
	
	public DoubtsQuery(){
		connection = DBConnection.getDBConnection();
		conn = connection.connect;
		gson = new Gson();
	}

	JSONObject getReplies(String uname,String dmsg,String drdt) {
		JSONObject jo = new JSONObject();
		jo.put("uname", uname);
		jo.put("dmsg", dmsg);
		jo.put("drdt",drdt);
		return jo;
	}
	
	
	
	JSONObject getDoubts(String did,String dquest,String uid,String ddt) throws SQLException{
		   JSONObject doubt = new JSONObject();
		   doubt.put("did",did);
		   doubt.put("dquest",dquest);
		   doubt.put("gtype","D");
		   doubt.put("uid",uid);
		   JSONArray ja = new JSONArray();
		   for(DoubtReply dr:repliesDB(did)) {
			   ja.add(getReplies(dr.getUname(),dr.getDmsg(),dr.getDrdt()));
		   }
		   doubt.put("replies",ja);
		   doubt.put("pdt",ddt);
		   return doubt;
	}
	
	public List<DoubtReply> repliesDB(String did) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select (select UNAME from users where UID=d.UID ) as uname,"
					+ "d.DMSG,d.DRDT from doubts_reply d where d.did=? order by d.DRDT desc;";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, did);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<DoubtReply> replies = new ArrayList<>();
		DoubtReply dr;
		while(resultSet.next()) {
			dr = new DoubtReply(
					resultSet.getString("uname"),
					resultSet.getString("DMSG"),
					resultSet.getString("DRDT")
					);
			replies.add(dr);
		}
		return replies;
	}
	
	@Override
	public String particularClass(String cid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select DID,DQUEST,UID,DDT as pdt from doubts where CID=? order by DDT desc";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, cid);
		ResultSet resultSet = preparedStatement.executeQuery();
		JSONArray fa = new JSONArray();
		while(resultSet.next()) {
			fa.add(
					getDoubts(
							resultSet.getString("DID"),
							resultSet.getString("DQUEST"),
							resultSet.getString("UID"),
							resultSet.getString("pdt")
							)
					);
			
		}
		return fa.toJSONString();
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
	
	//INSERT INTO doubts(DQUEST,UID,CID) VALUES('hi this is my question','UID-000001','CID-000001');

	public void addDoubt(String dquest,String uid,String cid) throws SQLException {
	// TODO Auto-generated method stub
	
		String sql = "INSERT INTO doubts(DQUEST,UID,CID) VALUES(?,?,?);";
		
		preparedStatement = conn
		          .prepareStatement(sql);
		
		preparedStatement.setString(1, dquest);
		preparedStatement.setString(2, uid);
		preparedStatement.setString(3, cid);
		
		
		preparedStatement.executeUpdate();
		System.out.println("doubt Added!");
	}
	//INSERT INTO doubts_reply(DID,UID,DMSG) values('DID-000008','UID-000001','some random comment just for example');
	
	public void addReply(String did,String uid,String dmsg) throws SQLException {
		// TODO Auto-generated method stub
		
			String sql = "INSERT INTO doubts_reply(DID,UID,DMSG) values(?,?,?)";
			
			preparedStatement = conn
			          .prepareStatement(sql);
			
			preparedStatement.setString(1, did);
			preparedStatement.setString(2, uid);
			preparedStatement.setString(3, dmsg);
			
			
			preparedStatement.executeUpdate();
			System.out.println("reply Added!");
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

class DoubtReply{

	String uname,dmsg,drdt;

	DoubtReply(String uname,String dmsg,String drdt){
		this.uname=uname;
		this.dmsg=dmsg;
		this.drdt=drdt;
	}

	public void setUname(String uname){ this.uname=uname; }

	public void setDmsg(String dmsg){ this.dmsg=dmsg; }

	public void setDrdt(String drdt){ this.drdt=drdt; }

	public String getUname(){ return this.uname; }

	public String getDmsg(){ return this.dmsg; }

	public String getDrdt(){ return this.drdt; }

}