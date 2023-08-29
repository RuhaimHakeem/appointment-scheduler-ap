package com.ruhaim.appointment.service;

import java.sql.SQLException;

import com.ruhaim.appointment.dao.UserManager;
import com.ruhaim.appointment.dao.UserManagerImpl;
import com.ruhaim.appointment.model.User;

public class UserService {
	
	private static UserService userServiceObj;

	public UserService() {
		
	}
	
	public synchronized static UserService getUserService() {
		
		if(userServiceObj == null) {
			userServiceObj = new UserService();
		}
		
		return userServiceObj;
	}
	
	private UserManager getUserManagerDao() {
		
		return new UserManagerImpl();
		
	}
	
	public User login(String username, String password, String role) throws ClassNotFoundException, SQLException {
		return getUserManagerDao().login(username, password, role);
	}

	
}
