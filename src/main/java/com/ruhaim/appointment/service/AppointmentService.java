package com.ruhaim.appointment.service;
import java.sql.SQLException;
import com.ruhaim.appointment.dao.AppointmentManager;
import com.ruhaim.appointment.dao.AppointmentManagerImpl;
import com.ruhaim.appointment.model.Appointment;


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
	
	public boolean bookAppointment(Appointment appointment, int userId) throws ClassNotFoundException, SQLException {
		return getAppointmentmanagerDao().bookAppointment(appointment, userId);
	}
	
//	public boolean registerConsultant(Consultant consultant, int regId) throws ClassNotFoundException, SQLException {
//		return getConsultantManagerDao().registerConsultant(consultant, regId);
//	}
//	
//	
//	public boolean deleteConsultant(int consultantId) throws ClassNotFoundException, SQLException {
//		return getConsultantManagerDao().deleteConsultant(consultantId);
//	}


}
