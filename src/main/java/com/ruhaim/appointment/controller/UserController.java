package com.ruhaim.appointment.controller;
import com.ruhaim.appointment.model.User;
import com.ruhaim.appointment.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private UserService getUserService() {
		return UserService.getUserService();
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		login(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		User user = new User();
//		user.setUserName(request.getParameter("username"));
//		user.setPassword(request.getParameter("password"));	
		

		
		String action = request.getParameter("action");	
		
		System.out.println("Hey" + action);

		
		String username = "Admin";
		String password = "1234";
		
		
		try {
			
			User userr = getUserService().login(username,password);
		
		

			String message = "Login successful!";
		    request.setAttribute("feedbackMessage", message);
		    System.out.println("Successfully received " + userr.getUsername());
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			 System.out.println("Login failed: " + e.getMessage());
			String message = "Login failed: " + e.getMessage();
			request.setAttribute("feedbackMessage", message);
		}
		
	
//		RequestDispatcher rd = request.getRequestDispatcher("add-product.jsp");
//		rd.forward(request, response);
	}

}
