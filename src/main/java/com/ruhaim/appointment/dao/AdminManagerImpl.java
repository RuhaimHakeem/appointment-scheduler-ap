package com.ruhaim.appointment.dao;

import java.sql.Connection;
import java.sql.SQLException;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalAppointmentsThisMonth() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

}
