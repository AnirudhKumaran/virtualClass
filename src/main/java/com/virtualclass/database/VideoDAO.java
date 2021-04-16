package com.virtualclass.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.virtualclass.model.Video;

public interface VideoDAO {
	
	public String getAllVideos(String cid) throws SQLException;
	//public List<Video> getAllVideos(String cid,String uid) throws SQLException;
	//public void addProduct(Product product) throws SQLException;
	public void updateVideo(String vid,int vstat) throws SQLException;
	//public void deleteProduct(String pid) throws SQLException;
	public void uploadVideo(String vtitle,String vcont,String fname,String uid,String cid) throws SQLException;

}
