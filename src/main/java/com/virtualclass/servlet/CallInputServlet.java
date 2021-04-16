package com.virtualclass.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.virtualclass.database.*;

/**
 * Servlet implementation class CallInputServlet
 */
@WebServlet("/formInput")
public class CallInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String dirName = "C:\\Users\\srini\\eclipse-workspace\\VirtualClass\\src\\main\\webapp\\videos\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
	DoubtsQuery dq;
	VideoQuery vq;
	StudentQuery sq;
    public CallInputServlet() {
        super();
        dq = new DoubtsQuery();
        vq = new VideoQuery();
        sq = new StudentQuery();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Web Path : "+request.getContextPath());
		int responseNumber = Integer.valueOf(request.getParameter("rnum"));
		String cid = "";
		String uid = "";
		String cname = "";
		String fname="",uname="",uaddr="",ucity="",ustate="",ucountry="";
		String upincode = "",umail = "";
		int utype=-1,uage = 0;
		
		RequestDispatcher dispatcher;
		ServletFileUpload sf;
		
		switch(responseNumber) {
		
			case 0:		//Ask Question
				String doubt = request.getParameter("doubtQuestion").toString();
				cname = request.getParameter("cname").toString();
				cid = request.getParameter("cid").toString();
				uid = request.getParameter("uid").toString();
				System.out.println("Doubt entered : "+doubt+","+cid+","+uid);
			try {
				dq.addDoubt(doubt,uid,cid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("studentClass.jsp?cid="+cid+"&&cname="+cname);
            //dispatcher.forward(request, response);
				break;
			
			case 1:	// add a reply
				String reply = request.getParameter("dreply").toString();
				String did = request.getParameter("did").toString();
				uid = request.getParameter("uid").toString();
				cid = request.getParameter("cid").toString();
				cname = request.getParameter("cname").toString();
				System.out.println("Info recieved : "+uid+","+did+","+cid+","+cname+","+reply);
			try {
				dq.addReply(did,uid,reply);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("studentClass.jsp?cid="+cid+"&&cname="+cname);
			break;
			
			case 2:	//add post
				uid = request.getParameter("uid").toString();
				cid = request.getParameter("cid").toString();
				cname = request.getParameter("cname").toString();
				String ptitle = request.getParameter("pTitle").toString();
				String pcontent = request.getParameter("pContent").toString();
			try {
				dq.addPost(ptitle,pcontent,uid,cid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("studentClass.jsp?cid="+cid+"&&cname="+cname);
				break;
				
			case 3:	//add video
				uid = request.getParameter("uid").toString();
				cid = request.getParameter("cid").toString();
				cname = request.getParameter("cname").toString();
				
				sf = new ServletFileUpload(new DiskFileItemFactory());
				String destination = "";
				String fileName = "";
			    
				try {
					List<FileItem> multifiles = sf.parseRequest(request);
					
					String vtitle = multifiles.get(0).getString();
					String vcontent = multifiles.get(1).getString();
					
					FileItem videoFile = multifiles.get(2);
					fileName = videoFile.getName();
					destination = dirName + fileName;
					videoFile.write(new File(destination));
					vq.uploadVideo(vtitle,vcontent,fileName,uid,cid);
					
					//System.out.println("Number Of Files : "+multifiles.size());
//					for(FileItem item:multifiles) {
//						System.out.println("File Item"+item);
//						fileName = item.getName();
//						destination = dirName + fileName;
//						item.write(new File(destination));
//						vq.uploadVideo("Test 2","Test 2",fileName,uid,cid);
//					}
					System.out.println("File Uploaded!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("studentClass.jsp?cid="+cid+"&&cname="+cname);
				break;
				
			case 4:	//Update Video
				String vid = request.getParameter("vid").toString();
				boolean flag = Boolean.valueOf(request.getParameter("approve").toString());
				int vstat = flag?1:0;
			try {
				vq.updateVideo(vid,vstat);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
				
			case 5:		//add user
				utype = Integer.parseInt(request.getParameter("utype"));
				fname = request.getParameter("fname");
				uname = request.getParameter("uname");
				uage = Integer.parseInt(request.getParameter("uage"));
				uaddr = request.getParameter("uaddr");
				ucity = request.getParameter("ucity");
				ustate = request.getParameter("ustate");
				ucountry = request.getParameter("ucountry");
				upincode = request.getParameter("upincode");
				umail = request.getParameter("umail");
				
			try {
				sq.insertUser(fname,uname,uage,utype,uaddr,ucity,ustate,ucountry,upincode,umail);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("adminboard.jsp");
			break;
			
			case 6:		// Update user
				uid = request.getParameter("uid");
				utype = Integer.parseInt(request.getParameter("utype"));
				fname = request.getParameter("fname");
				uname = request.getParameter("uname");
				uage = Integer.parseInt(request.getParameter("uage"));
				uaddr = request.getParameter("uaddr");
				ucity = request.getParameter("ucity");
				ustate = request.getParameter("ustate");
				ucountry = request.getParameter("ucountry");
				upincode = request.getParameter("upincode");
				umail = request.getParameter("umail");
				
			try {
				sq.updateUser(uid,fname,uname,uage,utype,uaddr,ucity,ustate,ucountry,upincode,umail);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.sendRedirect("adminboard.jsp");
			break;
			
		}
	}

}
