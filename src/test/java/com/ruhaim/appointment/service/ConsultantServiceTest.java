package com.ruhaim.appointment.service;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ruhaim.appointment.model.Consultant;

class ConsultantServiceTest {
	
	  private ConsultantService consultantService;
	  
	  @BeforeEach
		void setUp() throws Exception {
		  consultantService = ConsultantService.getConsultantService();
		}
	    
	@Test
	void testRegisterConsultant() {
		
		 String username = "ijaz";
		 String password = "1234";
	     String name = "ijaz Doe";
	     String email = "ijaz.doe@example.com";
	     String specializedJob = "Software Engineer";
	     String specializedCountry = "United States";
	     String role = "consultant";   
	     int regId = 980;

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
	void testDeleteConsultant() {
	   int consultantId = 7867;
	        
	   try {
	        boolean result = consultantService.deleteConsultant(consultantId);
       
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}
	
	@Test
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
