package com.ruhaim.appointment.dao;

import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;


public interface AppointmentManager {

	public boolean bookAppointment(Appointment appointment, int userId, int availabilityTimeId) throws ClassNotFoundException, SQLException;
	public List<AppointmentDetails>getAllAppointments(String status) throws SQLException, ClassNotFoundException;	
	public List<AppointmentDetails> getAppointmentsByJobSeeker(int userId, String status) throws SQLException, ClassNotFoundException;
	public List<AppointmentDetails> getAppointmentsByConsultant(int userId, String status) throws SQLException, ClassNotFoundException;
	public List<AppointmentDetails> getAppointmentsByConsultantAndJobSeeker(int consultantId, int jobSeekerid) throws SQLException, ClassNotFoundException;
	public boolean updateAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
	public boolean deleteAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
}
