package com.ruhaim.appointment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;

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
	     int availabilityTimeId = 45246;

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
	
	@Disabled
	@Test
	void testGetAppointmentsByJobSeeker() {
		 int userId = 54;
		 String status = "";
		 
		 List<AppointmentDetails> appointments = null;
	        
	   try {
		   appointments = appointmentService.getAppointmentsByJobSeeker(userId, status);
		   
		   if(appointments != null) {
			   assertNotNull(appointments);
		   }
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}

	@Disabled
	@Test
	void testUpdateAppointment() {
	
	   int appointmentId = 28;    
	        
	   try {
	        boolean result = appointmentService.updateAppointment(appointmentId);
	
	            
	        assertTrue(result);
	            
	      } catch (Throwable e) {
	         fail("Exception: " + e.getMessage());
	    }
	}


	@Test
	void testDeleteAppointment() {
		 int appointmentId = 25;    
	        
	   try {
		     boolean result = appointmentService.deleteAppointment(appointmentId);
		
		            
		      assertTrue(result);
		            
		    } catch (Throwable e) {
		         fail("Exception: " + e.getMessage());
		  }
	}

}
