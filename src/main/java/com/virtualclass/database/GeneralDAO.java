package com.virtualclass.database;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDAO {
	
	//public List<Product> getAllProducts() throws SQLException;
	//public void addProduct(Product product) throws SQLException;
	//public void updateProduct(Product product) throws SQLException;
	public String loginUser(String uname,String password,int utype) throws SQLException;
	//public boolean pidExist(String pid) throws SQLException;

}
