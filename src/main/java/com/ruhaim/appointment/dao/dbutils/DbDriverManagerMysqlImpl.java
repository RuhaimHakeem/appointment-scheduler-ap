package com.ruhaim.appointment.dao.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDriverManagerMysqlImpl implements DbDriverManager {


	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {	

		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/appointment_db";
		String userName = "root";
		String password = "12345678";
		
		return DriverManager.getConnection(url, userName, password);
	}

}
