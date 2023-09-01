package com.ruhaim.appointment.dao;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Statement;

import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;

public class AdminManagerImpl implements AdminManager {
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	@Override
	public int totalAppointmentsThisWeek() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();

	    Calendar calendar = Calendar.getInstance();

	    Calendar endDate = Calendar.getInstance();

	    calendar.add(Calendar.DAY_OF_YEAR, -7);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String startDateStr = dateFormat.format(calendar.getTime());
	    String endDateStr = dateFormat.format(endDate.getTime());

	    String query = "SELECT COUNT(*) AS total_appointments " +
	                   "FROM appointment " +
	                   "WHERE created_at BETWEEN ? AND ?";
	    
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, startDateStr);
	    ps.setString(2, endDateStr);

	    ResultSet rs = ps.executeQuery();
	    int totalAppointments = 0;
	    
	    if (rs.next()) {
	        totalAppointments = rs.getInt("total_appointments");
	    }

	    rs.close();
	    ps.close();
	    connection.close();

	    return totalAppointments;
	}

	@Override
	public int totalAppointmentsThisMonth() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();

	    Calendar calendar = Calendar.getInstance();

	    Calendar endDate = Calendar.getInstance();

	    calendar.add(Calendar.DAY_OF_YEAR, -30);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String startDateStr = dateFormat.format(calendar.getTime());
	    String endDateStr = dateFormat.format(endDate.getTime());
	    
	    

	    String query = "SELECT COUNT(*) AS total_appointments " +
	                   "FROM appointment " +
	                   "WHERE created_at BETWEEN ? AND ?";
	    
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, startDateStr);
	    ps.setString(2, endDateStr);

	    ResultSet rs = ps.executeQuery();
	    int totalAppointments = 0;
	    
	    if (rs.next()) {
	        totalAppointments = rs.getInt("total_appointments");
	    }

	    rs.close();
	    ps.close();
	    connection.close();

	    return totalAppointments;
	}
	
	@Override
	public int totalConsultants() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();

	    
	    

	    String query = "SELECT COUNT(*) AS total_consultants " +
	                   "FROM consultant";
	    
	    Statement st = connection.createStatement();
	

	    ResultSet rs = st.executeQuery(query);
	    int totalConsultants = 0;
	    
	    if (rs.next()) {
	    	totalConsultants = rs.getInt("total_consultants");
	    }

	    rs.close();
	    connection.close();

	    return totalConsultants;
	}
	
	@Override
	public int totalJobSeekers() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
    

	    String query = "SELECT COUNT(*) AS total_job_seekers " +
	                   "FROM job_seeker";
	    
	    Statement st = connection.createStatement();
	

	    ResultSet rs = st.executeQuery(query);
	    int totalJobSeekers = 0;
	    
	    if (rs.next()) {
	    	totalJobSeekers = rs.getInt("total_job_seekers");
	    }

	    rs.close();
	    connection.close();

	    return totalJobSeekers;
	}

}
