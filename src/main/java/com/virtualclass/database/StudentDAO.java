package com.virtualclass.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.virtualclass.model.Student;

public interface StudentDAO {
	
	public List<Student> getAllStudent(int utype) throws SQLException;
	//public void addProduct(Product product) throws SQLException;
	//public void updateProduct(Product product) throws SQLException;
	//public void deleteProduct(String pid) throws SQLException;
	public Map<String,String> getStudentdetails(String uid) throws SQLException;

}
