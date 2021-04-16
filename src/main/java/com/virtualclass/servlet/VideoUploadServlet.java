package com.virtualclass.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.virtualclass.database.VideoQuery;

/**
 * Servlet implementation class VideoUploadServlet
 */
@WebServlet("/uploadVideo")
public class VideoUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String dirName = "C:\\Users\\srini\\eclipse-workspace\\VirtualClass\\src\\main\\webapp\\videos\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	VideoQuery vq;
	
    public VideoUploadServlet() {
        super();
        vq = new VideoQuery();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		String destination = "";
		String fileName = "";
		try {
			List<FileItem> multifiles = sf.parseRequest(request);
			for(FileItem item:multifiles) {
				fileName = item.getName();
				destination = dirName + fileName;
				item.write(new File(destination));
				//vq.uploadVideo(fileName);
			}
			System.out.println("File Uploaded!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
