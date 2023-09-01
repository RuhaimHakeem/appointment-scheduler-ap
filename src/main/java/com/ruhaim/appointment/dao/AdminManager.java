package com.ruhaim.appointment.dao;

import java.sql.SQLException;

public interface AdminManager {
	public int totalAppointmentsThisWeek() throws ClassNotFoundException, SQLException;
	public int totalAppointmentsThisMonth() throws SQLException, ClassNotFoundException;
	public int totalConsultants() throws SQLException, ClassNotFoundException;
	public int totalJobSeekers() throws SQLException, ClassNotFoundException;
	
}
