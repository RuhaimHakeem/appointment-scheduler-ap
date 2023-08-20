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
import com.ruhaim.appointment.model.AppointmentDetails;
import com.ruhaim.appointment.model.AvailabilityTime;
import com.ruhaim.appointment.model.JobSeeker;

public class ConsultantAvailabilityTimeManagerImpl implements ConsultantAvailabilityTimeManager {
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	@Override
	public boolean addAvailabilityTime(AvailabilityTime availabilityTime, int userId)
			throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection();

		int consultantId = Helpers.fetchConsultantId(connection, userId);      

		String query = "INSERT INTO availability_time (date, time, consultant_id) VALUES (?,?,?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
       
        ps.setString(1, availabilityTime.getDate());
        ps.setString(2, availabilityTime.getTime());
        ps.setInt(3, consultantId);
        
        boolean result = false;
		
		if(ps.executeUpdate() > 0)
			result = true;		
		
		ps.close();
		connection.close();		
		return result;

       
	}

	@Override
	public List<AvailabilityTime> getAllAvailabiltyTimes() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		
	    
		 String query = "SELECT a.*, c.name AS consultant_name, c.specialized_job, c.specialized_country, c.email " +
                "FROM availability_time a " +
                "JOIN consultant c ON a.consultant_id = c.consultant_id ";
		 
	    Statement st = connection.createStatement();
	    
	    List<AvailabilityTime> availabilityTimes = new ArrayList<>();
	    
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
	    	AvailabilityTime availabilityTime = new AvailabilityTime(); 

	        availabilityTime.setName(rs.getString("consultant_name"));
	        availabilityTime.setDate(rs.getString("date"));
	        availabilityTime.setTime(rs.getString("time"));
	        availabilityTime.setSpecializedJob(rs.getString("specialized_job"));
	        availabilityTime.setSpecializedCountry(rs.getString("specialized_country"));
	        availabilityTime.setEmail(rs.getString("email"));
	        
	        availabilityTimes.add(availabilityTime);
	    }
	    
	    st.close();
	    connection.close();
	    
	    return availabilityTimes;
	}

	@Override
	public boolean deleteAvailabilityTime(int availabilityTimeId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String query = "DELETE FROM availability_time WHERE availability_time_id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, availabilityTimeId);
		
		boolean result = false;
		if(ps.executeUpdate() > 0) {
			result = true;
		}
		
		ps.close();
		connection.close();
		
		return result;
	}

	@Override
	public List<AvailabilityTime> getAvailabiltyTimesByJobAndCountry(String job, String country) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		
	    
		 String query = "SELECT a.*, c.name AS consultant_name, c.specialized_job, c.specialized_country, c.email "
		 		+ "FROM availability_time a "
		 		+ "JOIN consultant c ON a.consultant_id = c.consultant_id "
		 		+ "WHERE (c.specialized_job = ? OR ? IS NULL) AND (c.specialized_country = ? OR ? IS NULL)";
		 
		 
		 PreparedStatement ps = connection.prepareStatement(query);
		 ps.setString(1, job);       
		 ps.setString(2, job);      
		 ps.setString(3, country); 
		 ps.setString(4, country); 
	    
	    List<AvailabilityTime> availabilityTimes = new ArrayList<>();
	    
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	    	AvailabilityTime availabilityTime = new AvailabilityTime(); 

	        availabilityTime.setName(rs.getString("consultant_name"));
	        availabilityTime.setDate(rs.getString("date"));
	        availabilityTime.setTime(rs.getString("time"));
	        availabilityTime.setSpecializedJob(rs.getString("specialized_job"));
	        availabilityTime.setSpecializedCountry(rs.getString("specialized_country"));
	        availabilityTime.setEmail(rs.getString("email"));
	        
	        availabilityTimes.add(availabilityTime);
	    }
	    
	    ps.close();
	    connection.close();
	    
	    return availabilityTimes;
	}

}
