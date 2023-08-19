package com.ruhaim.appointment.controller;
import com.ruhaim.appointment.model.AppointmentDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.JobSeeker;
import com.ruhaim.appointment.service.AppointmentService;

/**
 * Servlet implementation class AppointmentController
 */
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String message = "";
	
	 private AppointmentService getAppointmentService() {
			return AppointmentService.getAppointmentService();
		}

    public AppointmentController() {
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		
		if(action != null) {
			if (action.equals("appointmentsbyJobSeeker")) {
	            getAppointmentsByJobSeeker(request, response);
	        } else if(action.equals("appointmentsByConsultant")) {
	        	getAppointmentsByConsultant(request,response);
	        } 
		} else {
			getAllAppointments(request, response);
		}
		
		        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("bookAppointment")) {
			bookAppointment(request, response);
		} else if (action.equals("deleteAppointment")) {
			deleteAppointment(request, response);
		} else if(action.equals("completeAppointment")) {
			updateAppointment(request, response);
		}

	}
	
	private void bookAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		 String date = request.getParameter("date");
	     String time = request.getParameter("time");
	     String status = "Booked";
	     int userId = Integer.parseInt(request.getParameter("userId")); 
	     int consultantId = Integer.parseInt(request.getParameter("consultantId")); 
	     int availabilityTimeId = Integer.parseInt(request.getParameter("availabilityTimeId")); 
	   
	     
	     Appointment appointment = new Appointment();
	     appointment.setDate(date);
	     appointment.setTime(time);
	     appointment.setStatus(status);
	     appointment.setConsultantId(consultantId);
	    
	     
	     try {
	    	 
	    	 if(getAppointmentService().bookAppointment(appointment, userId, availabilityTimeId)) {
	    		 
	    		 message = "The Appointment has been booked successfully!";
	    		 System.out.println("booked");
	    	
			} else {
				 message = "Failed to book the appoinment!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
	    	 message = "Failed to book the appoinment!";
	    	 System.out.println(e.getMessage());
		}
			
			request.setAttribute("feebackMessage", message);
//			RequestDispatcher rd = request.getRequestDispatcher("job-seeker-login.jsp");
//			rd.forward(request, response);
	     
		}
	
	private void getAllAppointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			appointmentDetails = getAppointmentService().getAllJobSeekers();
			
			if(appointmentDetails.isEmpty()) {
				message = "No record found";
			}
			for (AppointmentDetails appointment : appointmentDetails) {
			    System.out.println("Appointment ID: " + appointment.getAppointmentId());
			    System.out.println("Date: " + appointment.getDate());
			    System.out.println("Time: " + appointment.getTime());
			    System.out.println("Status: " + appointment.getStatus());
			    System.out.println("Consultant Name: " + appointment.getConsultantName());
			    System.out.println("Job Seeker Name: " + appointment.getJobSeekerName());
			    System.out.println("-----------------------------------");
			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			 System.out.println(e.getMessage());
			message = e.getMessage();
		}
		
		request.setAttribute("jobSeekers", appointmentDetails);
		request.setAttribute("feebackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);

	}
	
	private void getAppointmentsByJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userId = Integer.parseInt(request.getParameter("userId")); 

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			appointmentDetails = getAppointmentService().getAppointmentsByJobSeeker(userId);
			
			if(appointmentDetails.isEmpty()) {
				message = "No record found";
			}
			for (AppointmentDetails appointment : appointmentDetails) {
			    System.out.println("Appointment ID: " + appointment.getAppointmentId());
			    System.out.println("Date: " + appointment.getDate());
			    System.out.println("Time: " + appointment.getTime());
			    System.out.println("Status: " + appointment.getStatus());
			    System.out.println("Consultant Name: " + appointment.getConsultantName());
			    System.out.println("Job Seeker Name: " + appointment.getJobSeekerName());
			    System.out.println("-----------------------------------");
			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			 System.out.println(e.getMessage());
			 System.out.println("I am error here");
			message = e.getMessage();
		}
		
		request.setAttribute("appointments", appointmentDetails);
		request.setAttribute("feebackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);

	}
	
	private void getAppointmentsByConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userId = Integer.parseInt(request.getParameter("userId")); 

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			appointmentDetails = getAppointmentService().getAppointmentsByConsultant(userId);
			
			if(appointmentDetails.isEmpty()) {
				message = "No record found";
			}
			for (AppointmentDetails appointment : appointmentDetails) {
			    System.out.println("Appointment ID: " + appointment.getAppointmentId());
			    System.out.println("Date: " + appointment.getDate());
			    System.out.println("Time: " + appointment.getTime());
			    System.out.println("Status: " + appointment.getStatus());
			    System.out.println("Consultant Name: " + appointment.getConsultantName());
			    System.out.println("Job Seeker Name: " + appointment.getJobSeekerName());
			    System.out.println("-----------------------------------");
			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			 message = e.getMessage();
		}
		
		request.setAttribute("appointments", appointmentDetails);
		request.setAttribute("feebackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);

	}
	
	private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int appointmentid = Integer.parseInt(request.getParameter("appointmentId"));
		
		try {
			if(getAppointmentService().deleteAppointment(appointmentid)) {
				message = "The appointment has been successfully deleted";
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("feebackMessage", message);
	
		
//		response.sendRedirect("getproduct?actiontype=all");

	}
	
	private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		clearMessage();
		

		int appointmentid = Integer.parseInt(request.getParameter("appointmentId"));
		
		
		try {
			if(getAppointmentService().updateAppointment(appointmentid)) {
				message = "The appointment has been completed!";
			}
			else {
				message = "Failed to complete the appointment!";
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("feebackMessage", message);
//		RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
//		rd.forward(request, response);
		
	}
	

}
