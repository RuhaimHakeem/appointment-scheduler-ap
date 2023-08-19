package com.ruhaim.appointment.dao;

import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.Appointment;


public interface AppointmentManager {

	public boolean bookAppointment(Appointment appointment, int userid) throws ClassNotFoundException, SQLException;
	public List<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException;	
	public List<Appointment> getAppointmentsByJobSeeker(int jobSeekerId) throws SQLException, ClassNotFoundException;
	public List<Appointment> getAppointmentsByConsultant(int consultantId) throws SQLException, ClassNotFoundException;
	public boolean updateAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
	public boolean deleteAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
}
