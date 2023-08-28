package com.ruhaim.appointment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhaim.appointment.model.AvailabilityTime;
import com.ruhaim.appointment.service.ConsultantAvailabilityTimeService;




public class ConsultantAvailabilityTimeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String message = "";
       
   
    public ConsultantAvailabilityTimeController() {
        super();

    }
    
    private ConsultantAvailabilityTimeService getConsultantAvailabilityTimeService() {
		return ConsultantAvailabilityTimeService.getConsultantAvailabilityTimeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action != null) {
			if (action.equals("filter")) {
				getAvailabiltyTimesByJobAndCountry(request, response);
	        } else if (action.equals("filterByConsultant")) {
	        	getAvailabiltyTimesByConsultant(request,response);
	        }
		} else {
			getAllAvailabiltyTimes(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		
		if(action.equals("addAvailability")) {
			addAvailabilityTime(request, response);
		} else if(action.equals("deleteAvailability")) {
			deleteAvailabilityTime(request, response);
		}
	}
	
	private void addAvailabilityTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String date = request.getParameter("date");
	     String time = request.getParameter("time");
	     int userId = Integer.parseInt(request.getParameter("userId")); 
	     
	     AvailabilityTime availabilityTime = new AvailabilityTime();
	     availabilityTime.setDate(date);
	     availabilityTime.setTime(time);
	     
	     try {
	    	 
	    	 if(getConsultantAvailabilityTimeService().addAvailabilityTime(availabilityTime, userId)) {
	    		 
	    		message = "The Availability Time has been added successfully!";
	    		 System.out.println("added");
			} else {
				message = "Failed to add the availability time!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
				message = "operation failed! " + e.getMessage();
				System.out.println(e.getMessage());
			}
			
			request.setAttribute("feebackMessage", message);
//			RequestDispatcher rd = request.getRequestDispatcher("job-seeker-login.jsp");
//			rd.forward(request, response);
	}
	
	private void getAllAvailabiltyTimes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AvailabilityTime> availabilityTimes = new ArrayList<>();
		
		try 
		{
			availabilityTimes = getConsultantAvailabilityTimeService().getAllAvailabiltyTimes();
			
			if(availabilityTimes.isEmpty()) {
				message = "No record found";
			}
//			
//			for (AvailabilityTime availabilityTime : availabilityTimes) {    
//			    System.out.println("Availability Date: " + availabilityTime.getDate());
//			    System.out.println("Time: " + availabilityTime.getTime());
//			    System.out.println("Name: " + availabilityTime.getName());
//			    System.out.println("Country: " + availabilityTime.getSpecializedCountry());
//			    System.out.println("Job: " + availabilityTime.getSpecializedJob());
//			    System.out.println("Email: " + availabilityTime.getEmail());
//			    System.out.println("-----------------------------------");
//			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("availabilityTimes", availabilityTimes);
		request.setAttribute("feebackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);
	     
	}
	
	private void deleteAvailabilityTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int availabilityTimeId = Integer.parseInt(request.getParameter("availabilityTimeId"));
		
		try {
			if(getConsultantAvailabilityTimeService().deleteAvailabilityTime(availabilityTimeId)) {
				message = "The Availability Time has been successfully deleted";
			}
			System.out.println("deleted");
		} 
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("feebackMessage", message);

	}
	
	private void getAvailabiltyTimesByJobAndCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String job = request.getParameter("job");
	     String country = request.getParameter("country");
		
		List<AvailabilityTime> availabilityTimes = new ArrayList<>();
		
		try 
		{
			availabilityTimes = getConsultantAvailabilityTimeService().getAvailabiltyTimesByJobAndCountry(job, country);
			
			if(availabilityTimes.isEmpty()) {
				message = "No record found";
			}
			
			for (AvailabilityTime availabilityTime : availabilityTimes) {
			    System.out.println("Availability Date: " + availabilityTime.getDate());
			    System.out.println("Time: " + availabilityTime.getTime());
			    System.out.println("Country: " + availabilityTime.getSpecializedCountry());
			    System.out.println("Job: " + availabilityTime.getSpecializedJob());
			    System.out.println("Email: " + availabilityTime.getEmail());
			    System.out.println("-----------------------------------");
			}
			System.out.println(message);
		} 

		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		
		}
		
		request.setAttribute("availabilityTimes", availabilityTimes);
		request.setAttribute("feebackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);
	     
		

	}
	
private void getAvailabiltyTimesByConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId")); 
	
		List<AvailabilityTime> availabilityTimes = new ArrayList<>();
		
		try 
		{
			availabilityTimes = getConsultantAvailabilityTimeService().getAvailabiltyTimesByConsultant(userId);
			
			if(availabilityTimes.isEmpty()) {
				message = "No record found";
			}
			
			for (AvailabilityTime availabilityTime : availabilityTimes) {    
			    System.out.println("Availability Date: " + availabilityTime.getDate());
			    System.out.println("Time: " + availabilityTime.getTime());
			    System.out.println("Name: " + availabilityTime.getName());
			    System.out.println("Country: " + availabilityTime.getSpecializedCountry());
			    System.out.println("Job: " + availabilityTime.getSpecializedJob());
			    System.out.println("Email: " + availabilityTime.getEmail());
			    System.out.println("-----------------------------------");
			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("availabilityTimes", availabilityTimes);
		request.setAttribute("feebackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);
	     
	}
	

}
