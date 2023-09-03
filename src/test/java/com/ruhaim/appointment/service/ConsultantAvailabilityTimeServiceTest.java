package com.ruhaim.appointment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ruhaim.appointment.model.AvailabilityTime;

class ConsultantAvailabilityTimeServiceTest {

	private ConsultantAvailabilityTimeService consultantAvailabilityTimeService;
	  
	@BeforeEach
	void setUp() throws Exception {
		consultantAvailabilityTimeService = ConsultantAvailabilityTimeService.getConsultantAvailabilityTimeService();;
	}

	@Test
	void testAddAvailabilityTime() {
		 String date = "2023-09-07";
		 String time = "15:00";
	     int userId = 265;

	        AvailabilityTime availabilityTime = new AvailabilityTime();
	        
	        availabilityTime.setDate(date);
	        availabilityTime.setTime(time);
	        
	        
	   try {
	        boolean result = consultantAvailabilityTimeService.addAvailabilityTime(availabilityTime, userId);
	
	            
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	    	  
	         fail("Exception: " + e.getMessage());
	    }
	}

	@Test
	void testDeleteAvailabilityTime() {
	   int availabilityTimeId = 45245;
	        
	   try {
		     boolean result = consultantAvailabilityTimeService.deleteAvailabilityTime(availabilityTimeId);
	       
		     assertTrue(result);
		            
		   } catch (Throwable e) {
			   
		    fail("Exception: " + e.getMessage());
		}
	}

	@Test
	void testGetAvailabiltyTimesByConsultant() {
		 int userId = 24;
	        
	   try {
		   consultantAvailabilityTimeService.getAvailabiltyTimesByConsultant(userId);
     
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	      }
	    
	}

}
