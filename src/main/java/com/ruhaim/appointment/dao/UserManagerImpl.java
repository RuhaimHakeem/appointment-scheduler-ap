package com.ruhaim.appointment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;
import com.ruhaim.appointment.model.User;

public class UserManagerImpl implements UserManager {

	public UserManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	@Override
	public User login(String username, String password, String role) throws ClassNotFoundException, SQLException {

			Connection connection = getConnection();
				
				
				String query = "SELECT * FROM user WHERE username = ? AND role = ? ";
				
				try {
					PreparedStatement statement = connection.prepareStatement(query);
					
					statement.setString(1, username);
					statement.setString(2, role);
					
					ResultSet rs = statement.executeQuery();
					
					User user = new User();
					
					while(rs.next())
					{
						if(username.equals(rs.getString("username")) && password.equals(rs.getString("password")))
						{
							
							user.setUserId(rs.getInt("user_id"));
							user.setUserName(rs.getString("username"));
							user.setRole(rs.getString("role"));

						}		
						 
					}
					statement.close();
					connection.close();
					return user;
				} catch(SQLException e) {
					throw e;
				}			
					
				
				
		
	}

}
