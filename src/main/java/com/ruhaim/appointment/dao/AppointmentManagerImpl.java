package com.ruhaim.appointment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;
import com.ruhaim.appointment.dao.helpers.Helpers;
import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;

public class AppointmentManagerImpl implements AppointmentManager {

	public AppointmentManagerImpl() {
	
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	@Override
	public boolean bookAppointment(Appointment appointment, int userId, int availabilityTimeId) throws ClassNotFoundException, SQLException {
			Connection connection = getConnection();

			int jobSeekerId = Helpers.fetchJobSeekerId(connection, userId);      

	        String bookAppointmenttQuery = "INSERT INTO appointment (date, time, status, consultant_id, job_seeker_id) VALUES (?, ?, ?, ?, ?)";
	        String deleteAvailabilityTimeQuery = "DELETE FROM availability_time WHERE availability_time_id = ?";
	        
	        PreparedStatement appointmentPs = connection.prepareStatement(bookAppointmenttQuery);
	        appointmentPs.setString(1, appointment.getDate());
	        appointmentPs.setString(2, appointment.getTime());
	        appointmentPs.setString(3, appointment.getStatus());
	        appointmentPs.setInt(4, appointment.getConsultantId());
	        appointmentPs.setInt(5, jobSeekerId);

	        PreparedStatement deleteAvailabilityTimePs = connection.prepareStatement(deleteAvailabilityTimeQuery);
	        deleteAvailabilityTimePs.setInt(1, availabilityTimeId);
	        
	        connection.setAutoCommit(false);
	        
	        try {
	            int appointmentResult = appointmentPs.executeUpdate();
	            if (appointmentResult > 0) {
	                int deleteAvailabilityResult = deleteAvailabilityTimePs.executeUpdate();
	                if (deleteAvailabilityResult > 0) {
	                    connection.commit();
	                    return true;
	                } else {
	                    connection.rollback();
	                    return false;
	                   
	                }
	            } else {
	                connection.rollback();
	                return false;
	            }
	        } catch (SQLException e) {
	            connection.rollback();
	            return false;
	        } finally {
	            connection.setAutoCommit(true);
	            appointmentPs.close();
	            deleteAvailabilityTimePs.close();
	        }
	    } 
       

	@Override
	public List<AppointmentDetails> getAllAppointments() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		
	    
		 String query = "SELECT a.*, c.name AS consultant_name, j.name AS job_seeker_name " +
                 "FROM appointment a " +
                 "JOIN consultant c ON a.consultant_id = c.consultant_id " +
                 "JOIN job_seeker j ON a.job_seeker_id = j.job_seeker_id";
		 
	    Statement st = connection.createStatement();
	    
	    List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
	    
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
	        AppointmentDetails appointmentDetails = new AppointmentDetails(rs.getInt("appointment_id"), rs.getString("date"), rs.getString("time"), rs.getString("status"), rs.getString("consultant_name"), rs.getString("job_seeker_name")); 
	        appointmentDetailsList.add(appointmentDetails);
	    }
	    
	    st.close();
	    connection.close();
	    
	    return appointmentDetailsList;
	}

	@Override
	public List<AppointmentDetails> getAppointmentsByJobSeeker(int userId, String status) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		 
		 int jobSeekerId = Helpers.fetchJobSeekerId(connection, userId);

	    
		 String query = "SELECT a.*, c.name AS consultant_name, j.name AS job_seeker_name " +
                "FROM appointment a " +
                "JOIN consultant c ON a.consultant_id = c.consultant_id " +
                "JOIN job_seeker j ON a.job_seeker_id = j.job_seeker_id " +
                "WHERE a.job_seeker_id = ? AND (a.status = ? OR ? IS NULL)";
		 

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, jobSeekerId);
	    ps.setString(2, status);
	    ps.setString(3, status);
	    
	    List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
	    
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        AppointmentDetails appointmentDetails = new AppointmentDetails(rs.getInt("appointment_id"), rs.getString("date"), rs.getString("time"), rs.getString("status"), rs.getString("consultant_name"), rs.getString("job_seeker_name")); 
	        appointmentDetailsList.add(appointmentDetails);
	    }
	    
	    ps.close();
	    connection.close();
	    
	    return appointmentDetailsList;
	}
	

	@Override
	public List<AppointmentDetails> getAppointmentsByConsultant(int userId, String status) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		 
		 int consultantId = Helpers.fetchConsultantId(connection, userId);
	    
		 String query = "SELECT a.*, c.name AS consultant_name, j.name AS job_seeker_name " +
               "FROM appointment a " +
               "JOIN consultant c ON a.consultant_id = c.consultant_id " +
               "JOIN job_seeker j ON a.job_seeker_id = j.job_seeker_id " +
               "WHERE a.consultant_id = ? AND (a.status = ? OR ? IS NULL)";
		 

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, consultantId);
	    ps.setString(2, status);
	    ps.setString(3, status);
	    
	    List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
	    
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        AppointmentDetails appointmentDetails = new AppointmentDetails(rs.getInt("appointment_id"), rs.getString("date"), rs.getString("time"), rs.getString("status"), rs.getString("consultant_name"), rs.getString("job_seeker_name")); 
	        appointmentDetailsList.add(appointmentDetails);
	    }
	    
	    ps.close();
	    connection.close();
	    
	    return appointmentDetailsList;
	}

	@Override
	public boolean updateAppointment(int appointmentId) throws SQLException, ClassNotFoundException {

		Connection connection = getConnection();
		String query = "UPDATE appointment SET status=? WHERE appointment_id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, "Completed");
		ps.setInt(2, appointmentId);
	
		
		boolean result = false;		
		if(ps.executeUpdate() > 0)
			result = true;
		
		ps.close();
		connection.close();
		
		return result;
	}

	@Override
	public boolean deleteAppointment(int appointmentId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String query = "DELETE FROM appointment WHERE appointment_id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, appointmentId);
		
		boolean result = false;
		if(ps.executeUpdate() > 0) {
			result = true;
		}
		
		ps.close();
		connection.close();
		
		return result;
	}

}
