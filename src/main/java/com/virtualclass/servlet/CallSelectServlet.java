package com.virtualclass.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.virtualclass.database.*;

/**
 * Servlet implementation class CallSelectServlet
 */
@WebServlet("/selectquery")
public class CallSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	StudentQuery sq;
	PostQuery pq;
	VideoQuery vq;
	DoubtsQuery dq;
    public CallSelectServlet() {
        super();
        sq = new StudentQuery();
        pq = new PostQuery();
        vq = new VideoQuery();
        dq = new DoubtsQuery();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int responseNumber = Integer.valueOf(request.getParameter("rnum"));
		String cid;
		String uid;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.getWriter().write("Response Number : "+responseNumber);
		
		switch(responseNumber) {
		
			case 0:		//get class
				cid = request.getParameter("cid");
				break;
				
			case 1:		// get all students
				uid = request.getParameter("uid");
			try {
				write(response,sq.getStudentdetails(uid));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			case 2:	// get all post
				cid = request.getParameter("cid");
			try {
				write(response,pq.particularClass(cid));
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
			case 3:		//get all videos
				cid = request.getParameter("cid");
				try {
					write(response,vq.getAllVideos(cid));
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
					
			case 4:		// get all doubts
				cid = request.getParameter("cid");
				try {
					write(response,dq.particularClass(cid));
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
					
			case 7:
				uid = request.getParameter("uid");
			try {
				sq.deleteUser(uid);
				sq.deleteCredential(uid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("adminboard.jsp");
			break;
		}
		
	}

	private void write(HttpServletResponse response,String result) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}
	
	
	private void write(HttpServletResponse response,Map<String,String> map) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
}

