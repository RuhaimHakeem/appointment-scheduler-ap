package com.ruhaim.appointment.controller;
import com.ruhaim.appointment.model.AppointmentDetails;
import com.ruhaim.appointment.model.JobSeeker;
import com.ruhaim.appointment.model.Consultant;
import com.ruhaim.appointment.model.Email;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.service.AppointmentService;
import com.ruhaim.appointment.service.ConsultantService;
import com.ruhaim.appointment.service.EmailService;
import com.ruhaim.appointment.service.JobSeekerService;


public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String message = "";
	
	 private AppointmentService getAppointmentService() {
			return AppointmentService.getAppointmentService();
	}
	
	 private JobSeekerService getJobSeekerService() {
			return JobSeekerService.getJobSeekerService();
	}
	 
	 private ConsultantService getConsultantService() {
			return ConsultantService.getConsultantService();
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
			try {
				bookAppointment(request, response);
			} catch (ServletException | IOException| ClassNotFoundException | SQLException | MessagingException e) {
				e.printStackTrace();
			}
		} else if (action.equals("deleteAppointment")) {
			deleteAppointment(request, response);
		} else if(action.equals("completeAppointment")) {
			updateAppointment(request, response);
		}

	}
	
	private void bookAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, MessagingException {
		
		 
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
	    		 
	 			 sendEmail(userId, consultantId, date, time);
	 			 
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
	
	private void sendEmail(int userId, int consultantId, String date, String time) throws ClassNotFoundException, SQLException, MessagingException {
		 JobSeeker jobSeeker = getJobSeekerService().getJobSeekerById(userId);
	     Consultant consultant = getConsultantService().getConsultantById(consultantId);
	     
	     Email mailJobSeeker = new Email(jobSeeker.getName(), consultant.getName(), jobSeeker.getName(), jobSeeker.getEmail(), date, time);
	     
	     Email mailConsultant = new Email(jobSeeker.getName(), consultant.getName(), consultant.getName(), consultant.getEmail(), date, time);
	    
	     EmailService mailService = new EmailService();
	     
	     mailService.send(mailConsultant);
	     mailService.send(mailJobSeeker);
	}
	

}
