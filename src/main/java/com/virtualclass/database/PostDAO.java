package com.virtualclass.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PostDAO {
	
	//public List<Product> getAllProducts() throws SQLException;
	//public void addProduct(Product product) throws SQLException;
	//public void updateProduct(Product product) throws SQLException;
	//public void deleteProduct(String pid) throws SQLException;
	public String particularClass(String cid) throws SQLException;
	//public String getClassDescription(String cid) throws SQLException;
}
