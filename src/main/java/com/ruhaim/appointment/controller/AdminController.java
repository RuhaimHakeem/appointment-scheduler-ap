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

import com.ruhaim.appointment.model.AppointmentDetails;
import com.ruhaim.appointment.model.Consultant;
import com.ruhaim.appointment.model.JobSeeker;
import com.ruhaim.appointment.service.AdminService;
import com.ruhaim.appointment.service.AppointmentService;
import com.ruhaim.appointment.service.ConsultantService;
import com.ruhaim.appointment.service.JobSeekerService;


/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminController() {
        super();
    }
    
    private AdminService getAdminService() {
		return AdminService.getAdminService();
	}
    
    private ConsultantService getConsultantService() {
		return ConsultantService.getConsultantService();
	}
    
    private AppointmentService getAppointmentService() {
		return AppointmentService.getAppointmentService();
	}
    
    private JobSeekerService getJobSeekerService() {
		return JobSeekerService.getJobSeekerService();
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			totalAppointmentsThisWeek(request, response);
    		totalAppointmentsThisMonth(request,response);
    		totalConsultants(request,response);
    		totalJobSeekers(request,response);
    		getAllConsultants(request,response);
    		getAppointmentsByConsultant(request, response);
    		getAllJobSeekers(request,response);
    		getAppointmentsByJobSeeker(request, response);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("AdminReports.jsp");
        	rd.forward(request, response);
		} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {

		}
    	        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	
	private void totalAppointmentsThisWeek(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException
	{

		int count = 0;
		
		count = getAdminService().totalAppointmentsThisWeek();	
		
		request.setAttribute("totalAppointmentsThisWeek", count);
		

	}
	
	private void totalAppointmentsThisMonth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException
	{

		int count = 0;
		
		count = getAdminService().totalAppointmentsThisMonth();	
		
		System.out.println(count);
		
		request.setAttribute("totalAppointmentsThisMonth", count);
		

	}
	
	private void totalConsultants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException
	{

		int count = 0;
		
		count = getAdminService().totalConsultants();	
		
		
		request.setAttribute("totalConsultants", count);
		

	}
	
	private void totalJobSeekers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException
	{

		int count = 0;
		
		count = getAdminService().totalJobSeekers();	
		
		
		request.setAttribute("totalJobSeekers", count);
		

	}
	
	private void getAllConsultants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<Consultant> consultants = new ArrayList<>();
		 String job = request.getParameter("job");
	     String country = request.getParameter("country");
		
		try 
		{
			consultants = getConsultantService().getAllConsultants(job,country);
				

		} 
		catch (ClassNotFoundException | SQLException e) {
	
		}
		
		request.setAttribute("consultants", consultants);
		

	}
	
	private void getAppointmentsByConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int userId = 0;
		String userIdParam = request.getParameter("userId");

		if (userIdParam != null && !userIdParam.isEmpty()) {
		    userId = Integer.parseInt(userIdParam);
		}
		String status = request.getParameter("status"); 

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			if(userId != 0) {
				appointmentDetails = getAppointmentService().getAppointmentsByConsultant(userId, status);
			}
			else {
				appointmentDetails = getAppointmentService().getAllAppointments(status);
			}
			
			

		} 
		catch (ClassNotFoundException | SQLException | NumberFormatException  e) {
			
		}
		
		 request.setAttribute("appointments", appointmentDetails);


	}
	
	private void getAllJobSeekers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<JobSeeker> jobSeekers = new ArrayList<>();
		
		try 
		{
			jobSeekers = getJobSeekerService().getAllJobSeekers();
				

		} 
		catch (ClassNotFoundException | SQLException e) {

		}
		
		request.setAttribute("jobSeekers", jobSeekers);

	}
	

	private void getAppointmentsByJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userId = 0;
		String userIdParam = request.getParameter("userId");

		if (userIdParam != null && !userIdParam.isEmpty()) {
		    userId = Integer.parseInt(userIdParam);
		}
		
		String status = request.getParameter("status"); 

		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		
		try 
		{
			if(userId != 0) {
				appointmentDetails = getAppointmentService().getAppointmentsByJobSeeker(userId, status);
			}
			else {
				appointmentDetails = getAppointmentService().getAllAppointments(status);
			}
		

		} 
		catch (ClassNotFoundException | SQLException e) {
		}
		
		 request.setAttribute("appointments", appointmentDetails);


	}

}
