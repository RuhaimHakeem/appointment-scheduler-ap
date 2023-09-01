package com.ruhaim.appointment.service;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.dao.AdminManager;
import com.ruhaim.appointment.dao.AdminManagerImpl;
import com.ruhaim.appointment.dao.AppointmentManager;
import com.ruhaim.appointment.dao.AppointmentManagerImpl;
import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;



public class AdminService {
	
	private static AdminService adminServiceObj;

	public AdminService() {

	}
	
	public synchronized static AdminService getAdminService() {
		
		if(adminServiceObj == null) {
			adminServiceObj = new AdminService();
		}
		
		return adminServiceObj;
	}
	
	private AdminManager getAdminManagerDao() {

		return new AdminManagerImpl();
	
	}
	
	public int totalAppointmentsThisWeek() throws ClassNotFoundException, SQLException {
		return getAdminManagerDao().totalAppointmentsThisWeek();
	}
	
	public int totalAppointmentsThisMonth() throws ClassNotFoundException, SQLException {
		return getAdminManagerDao().totalAppointmentsThisMonth();
	}
//	
//	public List<AppointmentDetails> getAllJobSeekers(String status) throws ClassNotFoundException, SQLException {
//		return getAppointmentmanagerDao().getAllAppointments(status);
//	}
//	
//	public List<AppointmentDetails> getAppointmentsByJobSeeker(int userId, String status) throws ClassNotFoundException, SQLException {
//		return getAppointmentmanagerDao().getAppointmentsByJobSeeker(userId, status);
//	}
//	
//	public List<AppointmentDetails> getAppointmentsByConsultant(int userId, String status) throws ClassNotFoundException, SQLException {
//		return getAppointmentmanagerDao().getAppointmentsByConsultant(userId, status);
//	}	
//	
//	public boolean updateAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
//		return  getAppointmentmanagerDao().updateAppointment(appointmentId);
//	}
//	
//	public boolean deleteAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
//		return  getAppointmentmanagerDao().deleteAppointment(appointmentId);
//	}


}
