package com.ruhaim.appointment.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ruhaim.appointment.model.Consultant;
import com.ruhaim.appointment.service.ConsultantService;


/**
 * Servlet implementation class ConsultantController
 */
public class ConsultantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String message = "";
       
    public ConsultantController() {
       
    }
    
    private ConsultantService getConsultantService() {
		return ConsultantService.getConsultantService();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("registerConsultantId")) {
			registerConsultantWithId(request, response);
		}
		else if(action.equals("registerConsultant")) {
			registerConsultant(request, response);
		}
		else if(action.equals("deleteConsultant")) {
			deleteConsultant(request, response);
		}
		
	}
	
	private void registerConsultantWithId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int regId = Integer.parseInt(request.getParameter("regId")); 
	     
	     try {
	    	 
	    	 if(getConsultantService().registerConsultantWithId(regId)) {
	    		 
	    		message = "The Consultant registration id has been added successfully!";
	    		System.out.println("added");
	    		 
			} 
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
				message = "registration id already exists!";
		
			}
			
			request.setAttribute("feebackMessage", message);
//			RequestDispatcher rd = request.getRequestDispatcher("job-seeker-login.jsp");
//			rd.forward(request, response);
	     
		}
	
	private void registerConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 int regId = Integer.parseInt(request.getParameter("regId")); 
		 String username = request.getParameter("username");
	     String password = request.getParameter("password");
	     String name = request.getParameter("name");
	     String email = request.getParameter("email");
	     String specializedJob = request.getParameter("specializedJob");
	     String specializedCountry = request.getParameter("specializedCountry");
	     String role = "consultant";
	     
	     Consultant consultant = new Consultant();
	     consultant.setUserName(username);
	     consultant.setPassword(password);
	     consultant.setName(name);
	     consultant.setEmail(email);
	     consultant.setSpecializedJob(specializedJob);
	     consultant.setSpecializedCountry(specializedCountry);  
	     consultant.setRole(role);
	     
	     try {
	    	 
	    	 if(getConsultantService().registerConsultant(consultant, regId)) {
	    		 
	    		 message = "The Consultant has been registered successfully!";
	    	
			} else {
				 message = "Failed to register the Consultant!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
				message = "operation failed! " + e.getMessage();
			}
			
			request.setAttribute("feebackMessage", message);
//			RequestDispatcher rd = request.getRequestDispatcher("job-seeker-login.jsp");
//			rd.forward(request, response);
	     
		}
	
	private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int consultantId = Integer.parseInt(request.getParameter("consultantId"));
		
		try {
			if(getConsultantService().deleteConsultant(consultantId)) {
				message = "The consultant has been successfully deleted";
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			message = "Failed to delete the consultant!";
		}
		
		request.setAttribute("feebackMessage", message);
	
		
//		response.sendRedirect("getproduct?actiontype=all");

	}
	


}
