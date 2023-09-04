package com.ruhaim.appointment.dao.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Helpers {
	
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
	
}
