package com.ruhaim.appointment.controller;
import com.ruhaim.appointment.model.User;
import com.ruhaim.appointment.service.UserService;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String message = "";
	
	private UserService getUserService() {
		return UserService.getUserService();
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		login(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");	
		
		if (action.equals("login")) {
			login(request, response);
        } else if (action.equals("logout")) {
        	logout(request, response);
        }
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		clearMessage();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleToFetch = request.getParameter("role");	
		
		
		
		String dbUsername = null;
		String role = null;
		int userId = 0;
		
		
		try {
			
			User user = getUserService().login(username,password,roleToFetch);
			
			dbUsername = user.getUsername();
			role = user.getRole();
			userId = user.getUserId();
		
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			 System.out.println("Successfully received " + e.getMessage());
		}
		

		
		if(dbUsername == null)
		{
			   
			  message = "Login failed. Invalid username or password";
			  request.getSession().setAttribute("feedbackMessage", message);
			  String referringPage = request.getHeader("referer");
			    if (referringPage != null) {
			        response.sendRedirect(referringPage);
			    } 
	    	
		}
		
		else
		{
			RequestDispatcher rd = null;
			HttpSession session = request.getSession(); 
			session.setAttribute("username", dbUsername);
			session.setAttribute("userid", userId);
			session.setAttribute("role", role);
			
			
			if(role.equals("admin") ) {
				 rd = request.getRequestDispatcher("AdminDashboard.jsp");
			} else if(role.equals("job_seeker")) {
				rd = request.getRequestDispatcher("JobSeekerDashboard.jsp");
			} else if (role.equals("consultant")) {
				rd = request.getRequestDispatcher("ConsultantDashboard.jsp");
			}
				
			if(rd != null) {
		    	rd.forward(request, response);
			}
    
		}
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String role= request.getParameter("role");	
		RequestDispatcher rd = null;
        HttpSession session=request.getSession();  
        session.invalidate();
        
        if(role.equals("admin") ) {
			 rd = request.getRequestDispatcher("AdminLogin.jsp");
		} else if(role.equals("job_seeker")) {
			rd = request.getRequestDispatcher("JobSeekerLogin.jsp");
		} else if (role.equals("consultant")) {
			rd = request.getRequestDispatcher("ConsultantLogin.jsp");
		}
			
		if(rd != null) {
	    	rd.forward(request, response);
		}
        
	}
	
	private void clearMessage() {
		message = "";
	}

}
