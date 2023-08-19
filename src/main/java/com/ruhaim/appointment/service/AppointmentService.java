package com.ruhaim.appointment.service;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.dao.AppointmentManager;
import com.ruhaim.appointment.dao.AppointmentManagerImpl;
import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;



public class AppointmentService {
	
	private static AppointmentService appointmentServiceObj;

	public AppointmentService() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static AppointmentService getAppointmentService() {
		
		if(appointmentServiceObj == null) {
			appointmentServiceObj = new AppointmentService();
		}
		
		return appointmentServiceObj;
	}
	
	private AppointmentManager getAppointmentmanagerDao() {

		return new AppointmentManagerImpl();
	
	}
	
	public boolean bookAppointment(Appointment appointment, int userId, int availabilityTimeId) throws ClassNotFoundException, SQLException {
		return getAppointmentmanagerDao().bookAppointment(appointment, userId, availabilityTimeId);
	}
	
	public List<AppointmentDetails> getAllJobSeekers() throws ClassNotFoundException, SQLException {
		return getAppointmentmanagerDao().getAllAppointments();
	}
	
	public List<AppointmentDetails> getAppointmentsByJobSeeker(int userId) throws ClassNotFoundException, SQLException {
		return getAppointmentmanagerDao().getAppointmentsByJobSeeker(userId);
	}
	
	public List<AppointmentDetails> getAppointmentsByConsultant(int userId) throws ClassNotFoundException, SQLException {
		return getAppointmentmanagerDao().getAppointmentsByConsultant(userId);
	}	
	
	public boolean updateAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
		return  getAppointmentmanagerDao().updateAppointment(appointmentId);
	}
	
	public boolean deleteAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
		return  getAppointmentmanagerDao().deleteAppointment(appointmentId);
	}


}
