package com.ruhaim.appointment.dao;

import java.sql.SQLException;

import com.ruhaim.appointment.model.User;

public interface UserManager {
	
	public User login(String username, String password) throws ClassNotFoundException, SQLException;
}
  