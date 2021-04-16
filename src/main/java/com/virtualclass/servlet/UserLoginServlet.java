package com.virtualclass.servlet;

import com.virtualclass.database.GeneralQuery;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("uid");
        String password = request.getParameter("upass");
        int utype = Integer.parseInt(request.getParameter("userType"));
        System.out.println("Data recieved");
        GeneralQuery gq = new GeneralQuery();
         
        try {
            //User user = userDao.checkLogin(email, password);
        	String UID = "";
        	UID = gq.loginUser(email, password,utype);
            String destPage = "index.jsp";
             
            if (!UID.equals("SOS")) {
                HttpSession session = request.getSession();
                session.setAttribute("VCUID", UID);
                session.setAttribute("VCTYP", utype);
                String message = email;
                request.setAttribute("usernmae", message);
                if(utype==2)
                	destPage = "adminboard.jsp";
                else
                	destPage = "Home.jsp";
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }
             
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
             
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

}
