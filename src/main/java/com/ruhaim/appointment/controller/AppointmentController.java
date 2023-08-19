package com.ruhaim.appointment.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ruhaim.appointment.model.Appointment;
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
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("bookAppointment")) {
			bookAppointment(request, response);
		}
		

	}
	
	private void bookAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		 String date = request.getParameter("date");
	     String time = request.getParameter("time");
	     String status = "Booked";
	     int userId = Integer.parseInt(request.getParameter("userId")); 
	     int consultantId = Integer.parseInt(request.getParameter("consultantId")); 
	   
	     
	     Appointment appointment = new Appointment();
	     appointment.setDate(date);
	     appointment.setTime(time);
	     appointment.setStatus(status);
	     appointment.setConsultantId(consultantId);
	    
	     
	     try {
	    	 
	    	 if(getAppointmentService().bookAppointment(appointment, userId)) {
	    		 
	    		 message = "The Appointment has been booked successfully!";
	    		 System.out.println("booked");
	    	
			} else {
				 message = "Failed to book the appoinment!";
			}
	    	   
	     }
	     catch (ClassNotFoundException | SQLException e) {
				message = "operation failed! " + e.getMessage();
				 System.out.println("kjshfdkjdshfkhds?: " + e.getMessage());
			}
			
			request.setAttribute("feebackMessage", message);
//			RequestDispatcher rd = request.getRequestDispatcher("job-seeker-login.jsp");
//			rd.forward(request, response);
	     
		}

}
