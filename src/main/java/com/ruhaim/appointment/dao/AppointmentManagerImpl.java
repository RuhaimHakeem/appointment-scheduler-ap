package com.ruhaim.appointment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;
import com.ruhaim.appointment.model.Appointment;

public class AppointmentManagerImpl implements AppointmentManager {

	public AppointmentManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	@Override
	public boolean bookAppointment(Appointment appointment, int userId) throws ClassNotFoundException, SQLException {
		 Connection connection = getConnection();


            int jobSeekerId = fetchJobSeekerId(connection, userId);

            String query = "INSERT INTO appointment (date, time, status, consultant_id, job_seeker_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, appointment.getDate());
            ps.setString(2, appointment.getTime());
            ps.setString(3, appointment.getStatus());
            ps.setInt(4, appointment.getConsultantId());
            ps.setInt(5, jobSeekerId);
            
            return ps.executeUpdate() > 0;
       
	}

	@Override
	public List<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByJobSeeker(int jobSeekerId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByConsultant(int consultantId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAppointment(int appointmentId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAppointment(int appointmentId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
	
	 private int fetchJobSeekerId(Connection connection, int userId) throws SQLException {
	        String query = "SELECT job_seeker_id FROM job_seeker WHERE user_id = ?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, userId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("jobseeker_id");
	                }
	                
	                throw new SQLException("No matching job seeker found for user ID: " + userId);
	            }
	        }
	    }

}
