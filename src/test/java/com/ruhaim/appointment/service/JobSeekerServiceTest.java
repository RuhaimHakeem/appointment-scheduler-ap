package com.ruhaim.appointment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.ruhaim.appointment.model.JobSeeker;

class JobSeekerServiceTest {

  private JobSeekerService jobSeekerService;
	  
	  @BeforeEach
		void setUp() throws Exception {
		  jobSeekerService = JobSeekerService.getJobSeekerService();
		}

	@Test
	@Disabled
	void testRegiserJobSeeker() {
	     String username = "Adam";
	     String password = "1234";
	     String name = "Adam Wills";
	     String email = "Adam@gmail.com";
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
	
	
	@Disabled
	@Test
	void testRegiserJobSeekerWithUsedUserName() {
		
		 boolean result = false;
		
	     String username = "Adam";
	     String password = "1234";
	     String name = "Adam Wills";
	     String email = "Adam@gmail.com";
	     String role = "job_seeker";    

	     JobSeeker jobSeeker = new JobSeeker();
	     
	     jobSeeker.setUserName(username);
	     jobSeeker.setPassword(password);
	     jobSeeker.setName(name);
	     jobSeeker.setEmail(email);
	     jobSeeker.setRole(role);
	        
	        
	   try {
	         result = jobSeekerService.regiserJobSeeker(jobSeeker);
	
	            
	      } catch (Throwable e) {
		        assertFalse(result);
	    }
	}

	@Disabled
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
		 int userId = 73;
	        
		   try {
		        boolean result = jobSeekerService.deleteJobSeeker(userId);
	       
		        assertTrue(result);
		            
		      } catch (Throwable e) {
		         fail("Exception: " + e.getMessage());
		    }
	}

}
