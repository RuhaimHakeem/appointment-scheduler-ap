package com.ruhaim.appointment.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.ruhaim.appointment.model.Appointment;

class AppointmentServiceTest {

	 private AppointmentService appointmentService;
	  
	  @BeforeEach
		void setUp() throws Exception {
		  appointmentService = AppointmentService.getAppointmentService();;
		}

	@Disabled
	@Test
	void testBookAppointment() {
		 String date = "2023-09-05";
		 String time = "12:00";
	     String status = "Booked";
	     int userId = 63;
	     int consultantId = 2;
	     int availabilityTimeId = 45244;

	        Appointment appointment = new Appointment();
	        
	        appointment.setDate(date);
	        appointment.setTime(time);
	        appointment.setStatus(status);
	        appointment.setConsultantId(consultantId);
	        
	        
	   try {
	        boolean result = appointmentService.bookAppointment(appointment, userId, availabilityTimeId);
	
	            
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}
	
	
	@Test
	void testGetAppointmentsByJobSeeker() {
		 int userId = 54;
		 String status = "";
	        
	   try {
		   	 appointmentService.getAppointmentsByJobSeeker(userId, status);
      
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}

	@Test
	void testUpdateAppointment() {
	
	   int appointmentId = 27;    
	        
	   try {
	        boolean result = appointmentService.updateAppointment(appointmentId);
	
	            
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}

	@Test
	void testDeleteAppointment() {
		 int appointmentId = 27;    
	        
	   try {
		     boolean result = appointmentService.deleteAppointment(appointmentId);
		
		            
		      assertTrue(result);
		            
		    } catch (Throwable e) {
		         fail("Exception: " + e.getMessage());
		  }
	}

}
