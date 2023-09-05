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
    		getAppointmentsByConsultantAndJobSeeker(request, response);
    		getAllJobSeekers(request,response);
    		
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

	
	private void getAppointmentsByConsultantAndJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int consultantId = 0;
		int jobSeekerId = 0;
		
		List<AppointmentDetails> appointmentDetails = new ArrayList<>();
		String consultantIdParam = request.getParameter("consultantId");
		String jobSeekerIdParam = request.getParameter("jobSeekerId");

		if (consultantIdParam != null && !consultantIdParam.isEmpty()) {
		    consultantId = Integer.parseInt(consultantIdParam);
		}
		
		if (jobSeekerIdParam != null && !jobSeekerIdParam.isEmpty()) {
		    jobSeekerId = Integer.parseInt(jobSeekerIdParam);
		}
		
		try 
		{
			appointmentDetails = getAppointmentService().getAppointmentsByConsultantAndJobSeeker(consultantId, jobSeekerId);

		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
		
		request.setAttribute("appointments", appointmentDetails);
	

	}

}
