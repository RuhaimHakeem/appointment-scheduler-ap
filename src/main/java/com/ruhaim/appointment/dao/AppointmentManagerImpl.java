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
import com.ruhaim.appointment.model.Appointment;
import com.ruhaim.appointment.model.AppointmentDetails;
import com.ruhaim.appointment.model.JobSeeker;

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
	public boolean bookAppointment(Appointment appointment, int userId, int availabilityTimeId) throws ClassNotFoundException, SQLException {
			Connection connection = getConnection();
	        // Fetch the jobSeekerId based on the userId
	        int jobSeekerId = fetchJobSeekerId(connection, userId);
	        System.out.println(jobSeekerId);
	        

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
	        AppointmentDetails appointmentDetails = new AppointmentDetails( rs.getInt("appointment_id"), rs.getString("date"), rs.getString("time"), rs.getString("status"), rs.getString("consultant_name"), rs.getString("job_seeker_name")); 
	        appointmentDetailsList.add(appointmentDetails);
	    }
	    
	    st.close();
	    connection.close();
	    
	    return appointmentDetailsList;
	}

	@Override
	public List<AppointmentDetails> getAppointmentsByJobSeeker(int userId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		
		 int jobSeekerId = fetchJobSeekerId(connection, userId);
	    
		 String query = "SELECT a.*, c.name AS consultant_name, j.name AS job_seeker_name " +
                "FROM appointment a " +
                "JOIN consultant c ON a.consultant_id = c.consultant_id " +
                "JOIN job_seeker j ON a.job_seeker_id = j.job_seeker_id " +
                "WHERE a.job_seeker_id = ?";
		 

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, jobSeekerId);
	    
	    List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
	    
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        AppointmentDetails appointmentDetails = new AppointmentDetails( rs.getInt("appointment_id"), rs.getString("date"), rs.getString("time"), rs.getString("status"), rs.getString("consultant_name"), rs.getString("job_seeker_name")); 
	        appointmentDetailsList.add(appointmentDetails);
	    }
	    
	    ps.close();
	    connection.close();
	    
	    return appointmentDetailsList;
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
	                    return rs.getInt("job_seeker_id");
	                }
	                
	                throw new SQLException("No matching job seeker found for user ID: " + userId);
	            }
	        }
	   }
	 
	 private int fetchCounsultantId(Connection connection, int userId) throws SQLException {
	        String query = "SELECT consultant_id FROM consultant WHERE user_id = ?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, userId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("consultant_id");
	                }
	                
	                throw new SQLException("No matching consultant found for user ID: " + userId);
	            }
	        }
	   }

}
