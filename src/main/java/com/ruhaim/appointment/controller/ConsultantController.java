package com.ruhaim.appointment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		getAllConsultants(request, response);
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
		
		clearMessage();
		
		int regId = Integer.parseInt(request.getParameter("regId")); 
	     
	     try {
	    	 
	    	 if(getConsultantService().registerConsultantWithId(regId)) {
	    		 
	    		message = "The Consultant registration id has been added successfully!";
	    		 
			}  else {
				message = "operation failed! registration id already exists!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
		
			}
			
	     request.getSession().setAttribute("feedbackMessage", message);
	     response.sendRedirect("AdminAddNewConsultant.jsp");
	     
		}
	
	private void registerConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		clearMessage(); 
		
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
				 message = "operation failed! Unable to register the Consultant!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
	    	 message = "operation failed: Unable to register the Consultant!";
			}
			
	     	request.getSession().setAttribute("feedbackMessage", message);
	     	response.sendRedirect("ConsultantRegister.jsp");
	     
		}
	
	private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		clearMessage(); 
	
		
		int consultantId = Integer.parseInt(request.getParameter("consultantId"));
		
		try {
			if(getConsultantService().deleteConsultant(consultantId)) {
				message = "The consultant has been successfully deleted";
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			message = "operation failed! Unable to delete the consultant!";
		}
		
		
		request.getSession().setAttribute("feedbackMessage", message);
		
		response.sendRedirect("ConsultantManager");

	}
	
	private void getAllConsultants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		clearMessage(); 

		List<Consultant> consultants = new ArrayList<>();
		 String job = request.getParameter("job");
	     String country = request.getParameter("country");
		
		try 
		{
			consultants = getConsultantService().getAllConsultants(job,country);
			
			if(consultants.isEmpty()) {
				message = "No record found";
			}		

		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("consultants", consultants);
		request.setAttribute("feebackMessage", message);
		
		RequestDispatcher rd = request.getRequestDispatcher("AdminViewAllConsultants.jsp");
    	rd.forward(request, response);

	}
	
	private void clearMessage() {
		message = "";
	}
	


}
