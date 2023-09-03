package com.ruhaim.appointment.dao.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruhaim.appointment.model.JobSeeker;
import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;
import com.ruhaim.appointment.model.Consultant;

public class Helpers {
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}
	
	public static int fetchJobSeekerId(Connection connection, int userId) throws SQLException {
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
	
	public static int fetchConsultantId(Connection connection, int userId) throws SQLException {
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
	
	public static JobSeeker fetchJobSeekerById(int userId) throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
		
		   String query = "SELECT * FROM job_seeker WHERE user_id = ?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, userId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	 JobSeeker jobSeeker = new JobSeeker();
	                     jobSeeker.setUserId(rs.getInt("user_id"));
	                     jobSeeker.setName(rs.getString("name"));
	                     jobSeeker.setEmail(rs.getString("email"));
	                     
	                     return jobSeeker;
	                }
	                
	                throw new SQLException("No job seeker found for user ID: " + userId);
	            }
	        }
	}
	
	public static Consultant fetchConsultantById(int userId) throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
		
		   String query = "SELECT * FROM consultant WHERE consultant_id = ?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, userId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	 Consultant consultant = new Consultant();
	                	 consultant.setUserId(rs.getInt("user_id"));
	                	 consultant.setName(rs.getString("name"));
	                	 consultant.setEmail(rs.getString("email"));
	                     
	                     return consultant;
	                }
	                
	                throw new SQLException("No consultant found for user ID: " + userId);
	            }
	        }
	}
}
