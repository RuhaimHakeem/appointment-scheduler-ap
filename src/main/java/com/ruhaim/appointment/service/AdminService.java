package com.ruhaim.appointment.service;
import java.sql.SQLException;

import com.ruhaim.appointment.dao.AdminManager;
import com.ruhaim.appointment.dao.AdminManagerImpl;




public class AdminService {
	
	private static AdminService adminServiceObj;

	private AdminService() {

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
	public int totalConsultants() throws ClassNotFoundException, SQLException {
		return getAdminManagerDao().totalConsultants();
	}
	public int totalJobSeekers() throws ClassNotFoundException, SQLException {
		return getAdminManagerDao().totalJobSeekers();
	}




}
