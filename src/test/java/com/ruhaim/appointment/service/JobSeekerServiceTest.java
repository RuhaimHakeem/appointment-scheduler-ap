package com.ruhaim.appointment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ruhaim.appointment.model.JobSeeker;

class JobSeekerServiceTest {

  private JobSeekerService jobSeekerService;
	  
	  @BeforeEach
		void setUp() throws Exception {
		  jobSeekerService = JobSeekerService.getJobSeekerService();
		}

	@Test
	void testRegiserJobSeeker() {
	     String username = "Siva";
	     String password = "1234";
	     String name = "Siva Doe";
	     String email = "Siva.doe@example.com";
	     String role = "job_seeker";    

	     JobSeeker jobSeeker = new JobSeeker();
	     
	     jobSeeker.setUserName(username);
	     jobSeeker.setPassword(password);
	     jobSeeker.setName(name);
	     jobSeeker.setEmail(email);
	     jobSeeker.setRole(role);
	        
	        
	   try {
	        boolean result = jobSeekerService.regiserJobSeeker(jobSeeker);
	
	            
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}

	@Test
	void testGetAllJobSeekers() {
	        
	   try {
		   	jobSeekerService.getAllJobSeekers();
      
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}

	
	@Test
	void testDeleteJobSeeker() {
		 int jobSeekerId = 40;
	        
		   try {
		        boolean result = jobSeekerService.deleteJobSeeker(jobSeekerId);
	       
		        assertTrue(result);
		            
		      } catch (Throwable e) {
		         fail("Exception: " + e.getMessage());
		    }
	}

}
