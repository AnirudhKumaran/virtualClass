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

import com.virtualclass.model.Student;

public class StudentQuery implements StudentDAO {

	DBConnection connection;
	Connection conn;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	//List<Product> products;
	
	public StudentQuery(){
		connection = DBConnection.getDBConnection();
		conn = connection.connect;
	}

	@Override
	public Map<String,String> getStudentdetails(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT D.UID,D.UNAME,(SELECT UNAME from users where UID=D.UID) as LNAME,"
						+ "D.UAGE,D.UADDR,D.UCITY,D.USTATE,D.UCOUNTRY,D.UPINCODE,D.UEMAIL FROM "
						+ "users_details D where UID=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uid);
		System.out.println("Query processed!");
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		Map<String,String> datas = new HashMap<>();
		datas.put("UID",resultSet.getString("UID"));
		datas.put("FNAME",resultSet.getString("UNAME"));
		datas.put("LNAME",resultSet.getString("LNAME"));
		datas.put("UAGE",resultSet.getString("UAGE"));
		datas.put("UADDR",resultSet.getString("UADDR"));
		datas.put("UCITY",resultSet.getString("UCITY"));
		datas.put("USTATE",resultSet.getString("USTATE"));
		datas.put("UCOUNTRY",resultSet.getString("UCOUNTRY"));
		datas.put("UPINCODE",resultSet.getString("UPINCODE"));
		datas.put("UEMAIL",resultSet.getString("UEMAIL"));
		return datas;
	}
	
	public String getUID(String uname,String umail) throws SQLException {
		String sql = "SELECT UID FROM users_details where uname = ? and uemail=? limit 1";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uname);
		preparedStatement.setString(2, umail);
		System.out.println("getUID processed!");
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getString("UID");
	}
	
	public void insertUser(String fname,String uname,int age,int utype,String uaddr,String ucity,String ustate,String ucountry,String upincode,String umail) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = " INSERT INTO users_details(uname,uage,uaddr,ucity,ustate,"
					+ "ucountry,upincode,uemail) values(?,?,?,?,?,?,?,?)";
		
		preparedStatement = conn
		          .prepareStatement(sql);
		
//		System.out.println("Product Name : "+product.getPname());
		
		preparedStatement.setString(1, fname);
		preparedStatement.setInt(2, age);
		preparedStatement.setString(3, uaddr);
		preparedStatement.setString(4, ucity);
		preparedStatement.setString(5, ustate);
		preparedStatement.setString(6, ucountry);
		preparedStatement.setString(7, upincode);
		preparedStatement.setString(8, umail);

		
		preparedStatement.executeUpdate();
		System.out.println("User Added!");
		
		insertCredentials(getUID(fname,umail),uname,utype);
	}
	
	public void insertCredentials(String uid,String uname,int utype) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = " INSERT INTO users values(?,?,?,'1234')";
		
		preparedStatement = conn
		          .prepareStatement(sql);
		
//		System.out.println("Product Name : "+product.getPname());
		
		preparedStatement.setString(1, uid);
		preparedStatement.setInt(2, utype);
		preparedStatement.setString(3, uname);
				
		preparedStatement.executeUpdate();
		System.out.println("Credentials Added!");
	}

	public void updateUser(String uid,String fname,String uname,int age,int utype,String uaddr,String ucity,String ustate,String ucountry,String upincode,String umail) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE users_details SET uname = ? ,uage =? ,uaddr = ?,ucity = ? ,ustate = ?,"
					+ "ucountry = ?,upincode = ?,uemail = ? where uid = ?";
		
		preparedStatement = conn
		          .prepareStatement(sql);
		
//		System.out.println("Product Name : "+product.getPname());
		
		preparedStatement.setString(1, fname);
		preparedStatement.setInt(2, age);
		preparedStatement.setString(3, uaddr);
		preparedStatement.setString(4, ucity);
		preparedStatement.setString(5, ustate);
		preparedStatement.setString(6, ucountry);
		preparedStatement.setString(7, upincode);
		preparedStatement.setString(8, umail);
		preparedStatement.setString(9, uid);
		
		
		preparedStatement.executeUpdate();
		System.out.println("User Added!");
		
		updateCredentials(uid,uname);
	}
	
	public void deleteUser(String uid) throws SQLException {
		String sql = "DELETE FROM users_details where UID = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uid);
		System.out.println("student deleted");
		preparedStatement.executeUpdate();
	}
	
	public void deleteCredential(String uid) throws SQLException {
		String sql = "DELETE FROM users where UID = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uid);
		System.out.println("credential deleted!");
		preparedStatement.executeUpdate();
	}
	
	public void updateCredentials(String uid,String uname) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE users SET UNAME = ? WHERE UID = ?";
		
		preparedStatement = conn
		          .prepareStatement(sql);
		
//		System.out.println("Product Name : "+product.getPname());
		
		preparedStatement.setString(1, uname);
		preparedStatement.setString(2, uid);
				
		preparedStatement.executeUpdate();
		System.out.println("Credentials Added!");
	}
	
	@Override
	public List<Student> getAllStudent(int utype) throws SQLException {
		// TODO Auto-generated method stub
		List<Student> students  = new ArrayList<Student>();
		statement = conn.createStatement();
		
		String sql = "select UID,UNAME,UEMAIL,UCITY from users_details where "
					+" uid in (select uid from users where utype="+utype+")";
		
		resultSet = statement
		          .executeQuery(sql);
		
		Student s;
		
		while (resultSet.next()) {
			s = new Student(
					resultSet.getString("UID"),
					resultSet.getString("UNAME"),
					resultSet.getString("UEMAIL"),
					resultSet.getString("UCITY")
					);		     
			students.add(s);
		}
		
		return students;
	}

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