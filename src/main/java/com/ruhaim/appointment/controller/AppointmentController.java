package com.ruhaim.appointment.controller;
import com.ruhaim.appointment.model.AppointmentDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.service.AppointmentService;


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
	     
	     System.out.println(date);
	     System.out.println(time);
	     System.out.println(status);
	     System.out.println(userId);
	     System.out.println(consultantId);
	     System.out.println(availabilityTimeId);
	   
	     
	     Appointment appointment = new Appointment();
	     appointment.setDate(date);
	     appointment.setTime(time);
	     appointment.setStatus(status);
	     appointment.setConsultantId(consultantId);
	    
	    
	     
	     try {
	    	 
	    	 if(getAppointmentService().bookAppointment(appointment, userId, availabilityTimeId)) {
	    		 
	    		 message = "The Appointment has been booked successfully!";
	    	
			} else {
				 message = "operation failed! Unable to book the appoinment!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
	    	 System.out.println(e.getMessage());
		}
			
	     	request.getSession().setAttribute("feedbackMessage", message);
	     	response.sendRedirect("AvailabilityManager");
	     
		}
	
	private void getAllAppointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		String status = request.getParameter("status");
		
		try 
		{
			appointmentDetails = getAppointmentService().getAllAppointments(status);
			
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
		
		request.setAttribute("appointments", appointmentDetails);
		request.setAttribute("feebackMessage", message);
		
		RequestDispatcher rd = request.getRequestDispatcher("AdminViewAllAppointments.jsp");
    	rd.forward(request, response);

	}
	
	private void getAppointmentsByJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userId = Integer.parseInt(request.getParameter("userId")); 
		String status = request.getParameter("status"); 

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			appointmentDetails = getAppointmentService().getAppointmentsByJobSeeker(userId, status);
			
			if(appointmentDetails.isEmpty()) {
				message = "No record found";
			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		 request.setAttribute("appointments", appointmentDetails);
		
		RequestDispatcher rd = request.getRequestDispatcher("JobSeekerAppointments.jsp");
    	rd.forward(request, response);

	}
	
	private void getAppointmentsByConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userId = Integer.parseInt(request.getParameter("userId")); 
		String status = request.getParameter("status"); 

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			appointmentDetails = getAppointmentService().getAppointmentsByConsultant(userId, status);
			
			if(appointmentDetails.isEmpty()) {
				message = "No record found";
			}

		} 
		catch (ClassNotFoundException | SQLException e) {
			 message = e.getMessage();
		}
		
		 request.setAttribute("appointments", appointmentDetails);
		
		RequestDispatcher rd = request.getRequestDispatcher("ConsultantAppointments.jsp");
    	rd.forward(request, response);

	}
	
	private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int appointmentid = Integer.parseInt(request.getParameter("appointmentId"));
		String role = request.getParameter("role"); 
		
		try {
			if(getAppointmentService().deleteAppointment(appointmentid)) {
				message = "The appointment has been successfully deleted";
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			message = "operation failed! Unable to delete appointment";
		}
		
		request.getSession().setAttribute("feedbackMessage", message);
		
		HttpSession session = request.getSession();
	    int userId = (int) session.getAttribute("userid");
	    
	    if(role != null) {
	    	if(role.equals("admin")) {
	    		response.sendRedirect("AppointmentManager");
	    	}
	    }
	    else {
	    	response.sendRedirect("AppointmentManager?action=appointmentsbyJobSeeker&userId=" + userId);
	    }
		
		

	}
	
	private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		int appointmentid = Integer.parseInt(request.getParameter("appointmentId"));
		
		
		try {
			if(getAppointmentService().updateAppointment(appointmentid)) {
				message = "The appointment has been completed!";
			}
			else {
				message = "operation failed! Failed to complete the appointment!";
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		 request.getSession().setAttribute("feedbackMessage", message);
		 HttpSession session = request.getSession();
		 int userId = (int) session.getAttribute("userid");
			
		 response.sendRedirect("AppointmentManager?action=appointmentsByConsultant&userId=" + userId);
		
	}
	

}
