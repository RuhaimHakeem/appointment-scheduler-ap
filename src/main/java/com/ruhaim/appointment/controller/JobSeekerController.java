package com.ruhaim.appointment.controller;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.JobSeeker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ruhaim.appointment.service.JobSeekerService;

/**
 * Servlet implementation class JobSeekerController
 */
public class JobSeekerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String message = "";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("action");
		
			getAllJobSeekers(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("register")) {
			regiserJobSeeker(request, response);
		}
		else if(action.equals("delete")) {
			deleteJobSeeker(request, response);
		}
		
	}
	
	
	private JobSeekerService getJobSeekerService() {
		return JobSeekerService.getJobSeekerService();
	}
	
	private void regiserJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	clearMessage();
		
	 String username = request.getParameter("username");
     String password = request.getParameter("password");
     String name = request.getParameter("name");
     String email = request.getParameter("email");
     String role = "job_seeker";
     
     JobSeeker jobSeeker = new JobSeeker();
     jobSeeker.setUserName(username);
     jobSeeker.setPassword(password);
     jobSeeker.setName(name);
     jobSeeker.setEmail(email);
     jobSeeker.setRole(role);
     
     try {
    	 
    	 if(getJobSeekerService().regiserJobSeeker(jobSeeker)) {
    		 
    		message = "The Job seeker has been registered successfully!";
		} else {
			message = "Failed to register the Job Seeker!";
		}
    	   
     }
     catch (ClassNotFoundException | SQLException e) {
			message = "operation failed! " + e.getMessage();
		}
		
     	request.getSession().setAttribute("feedbackMessage", message);
		RequestDispatcher rd = request.getRequestDispatcher("JobSeekerRegister.jsp");
		rd.forward(request, response);
     
	}
	
	private void getAllJobSeekers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		clearMessage();

		List<JobSeeker> jobSeekers = new ArrayList<>();
		
		try 
		{
			jobSeekers = getJobSeekerService().getAllJobSeekers();
			
			if(jobSeekers.isEmpty()) {
				message = "No record found";
			}			

		} 
		catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("jobSeekers", jobSeekers);
		request.getSession().setAttribute("feedbackMessage", message);
		
//		RequestDispatcher rd = request.getRequestDispatcher("superAdminViewAdmins.jsp");
//    	rd.forward(request, response);

	}
	
	private void deleteJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		clearMessage();
		
		int jobSeekerId = Integer.parseInt(request.getParameter("jobSeekerId"));
		
		try {
			if(getJobSeekerService().deleteJobSeeker(jobSeekerId)) {
				message = "The job seeker has been successfully deleted";
			}
			else {
				message = "Failed to delete the job seeker!";
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
		request.getSession().setAttribute("feedbackMessage", message);
		

	
		
//		response.sendRedirect("getproduct?actiontype=all");

	}
	
	private void clearMessage() {
		message = "";
	}

}
