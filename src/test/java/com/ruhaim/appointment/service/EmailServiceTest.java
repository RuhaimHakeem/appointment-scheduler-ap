package com.ruhaim.appointment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ruhaim.appointment.model.Email;

class EmailServiceTest {

	private EmailService emailService;
	
	 @BeforeEach
		void setUp() throws Exception {
		 emailService = new EmailService();
		}

		@Test
		void testSendEmail() {
				
			boolean result = false;
			
			String jobSeekerName = "Muntha";
			String consultantName = "Khan";
		    String recipientName = "Muntha";
		    String recipientEmail = "munthasir911@gmail.com";
		    String date = "2023-09-15";
		    String time = "22:00";
		  
		        
		    Email mailJobSeeker = new Email(jobSeekerName, consultantName, recipientName, recipientEmail, date, time);
		        
		        
		   try {
			   result = emailService.send(mailJobSeeker);
			   
		            
		        assertTrue(result);
		            
		      } catch (Throwable e) {
		         fail("Exception: " + e.getMessage());
		    }
	    
		}

}
