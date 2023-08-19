package com.ruhaim.appointment.dao;

import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;


public interface AppointmentManager {

	public boolean bookAppointment(Appointment appointment, int userid, int availabilityTimeId) throws ClassNotFoundException, SQLException;
	public List<AppointmentDetails>getAllAppointments() throws SQLException, ClassNotFoundException;	
	public List<AppointmentDetails> getAppointmentsByJobSeeker(int userId) throws SQLException, ClassNotFoundException;
	public List<Appointment> getAppointmentsByConsultant(int userId) throws SQLException, ClassNotFoundException;
	public boolean updateAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
	public boolean deleteAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
}
