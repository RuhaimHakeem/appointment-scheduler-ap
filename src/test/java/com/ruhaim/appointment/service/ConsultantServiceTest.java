package com.ruhaim.appointment.service;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ruhaim.appointment.model.Consultant;

class ConsultantServiceTest {
	
	  private ConsultantService consultantService;
	  
	  @BeforeEach
		void setUp() throws Exception {
		  consultantService = ConsultantService.getConsultantService();
		}
	    
	@Test
	@Disabled
	void testRegisterConsultantWithValidRegId() {
		
		 String username = "John";
		 String password = "4567";
	     String name = "John Doe";
	     String email = "John@gmail.com";
	     String specializedJob = "Software Engineer";
	     String specializedCountry = "America";
	     String role = "consultant";   
	     int regId = 850;

	        Consultant consultant = new Consultant();
	        
	        consultant.setUserName(username);
	        consultant.setPassword(password);
	        consultant.setName(name);
	        consultant.setEmail(email);
	        consultant.setSpecializedJob(specializedJob);
	        consultant.setSpecializedCountry(specializedCountry);
	        consultant.setRole(role);
	        
	        
	   try {
	        boolean result = consultantService.registerConsultant(consultant, regId);
	
	            
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
    
	}

	@Test
	void testRegisterConsultantWithInvalidRegId() {
		boolean result = false;
		
		 String username = "Michael";
		 String password = "4567";
	     String name = "Michael Call";
	     String email = "Michael@gmail.com";
	     String specializedJob = "Software Engineer";
	     String specializedCountry = "America";
	     String role = "consultant";   
	     int regId = 3454;

	        Consultant consultant = new Consultant();
	        
	        consultant.setUserName(username);
	        consultant.setPassword(password);
	        consultant.setName(name);
	        consultant.setEmail(email);
	        consultant.setSpecializedJob(specializedJob);
	        consultant.setSpecializedCountry(specializedCountry);
	        consultant.setRole(role);
	        
	        
	   try {
	        result = consultantService.registerConsultant(consultant, regId);
	            
	      } catch (Throwable e) {
	    	  assertFalse(result);
	    }
    
	}
	
	@Test
	@Disabled
	void testRegisterConsultantWithUsedRegId() {
		
		boolean result = false;
		
		 String username = "Michael";
		 String password = "4567";
	     String name = "Michael Call";
	     String email = "Michael@gmail.com";
	     String specializedJob = "Software Engineer";
	     String specializedCountry = "America";
	     String role = "consultant";   
	     int regId = 2323;

	        Consultant consultant = new Consultant();
	        
	        consultant.setUserName(username);
	        consultant.setPassword(password);
	        consultant.setName(name);
	        consultant.setEmail(email);
	        consultant.setSpecializedJob(specializedJob);
	        consultant.setSpecializedCountry(specializedCountry);
	        consultant.setRole(role);
	        
	        
	   try {
	        result = consultantService.registerConsultant(consultant, regId);
	            
	      } catch (Throwable e) {
	    	  assertFalse(result);
	    }
    
	}
	
	@Disabled
	@Test
	void testDeleteConsultant() {
	   int userId = 71;
	        
	   try {
	        boolean result = consultantService.deleteConsultant(userId);
       
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}
	
	@Test
	@Disabled
	void testGetAllConsultants() {
		 String job = "";
		 String country = "";
	        
	   try {
	        consultantService.getAllConsultants(job,country);
      
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}

}
