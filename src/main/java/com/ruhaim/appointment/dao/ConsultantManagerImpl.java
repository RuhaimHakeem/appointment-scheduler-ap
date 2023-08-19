package com.ruhaim.appointment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;
import com.ruhaim.appointment.model.Consultant;

public class ConsultantManagerImpl implements ConsultantManager {

	public ConsultantManagerImpl() {

	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	@Override
	public boolean registerConsultantWithId(int regId) throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection();
		
		String query = "INSERT INTO consultant_reg (reg_id, used) VALUES (?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, regId);
		ps.setByte(2, (byte) 0);
		
		boolean result = false;
		
		if(ps.executeUpdate() > 0)
			result = true;		
		
		ps.close();
		connection.close();		
		return result;
	}

	@Override
	public boolean registerConsultant(Consultant consultant, int regId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		
		
		String checkRegIdQuery = "SELECT used FROM consultant_reg WHERE reg_id = ?";
		String updateRegIdUsedQuery = "UPDATE consultant_reg SET used = 1 WHERE reg_id = ?";
		String userQuery = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        String consultantQuery = "INSERT INTO consultant (name, email, specialized_job, specialized_country, user_id) VALUES (?, ?, ?, ?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            PreparedStatement checkRegIdPs = connection.prepareStatement(checkRegIdQuery);
            checkRegIdPs.setInt(1, regId);
            
            ResultSet regIdResult = checkRegIdPs.executeQuery();
            if (!regIdResult.next() || regIdResult.getInt("used") != 0) {
                throw new SQLException("Invalid or used registration ID.");
            }
            
            // Update the registration ID as used
            PreparedStatement updateRegIdUsedPs = connection.prepareStatement(updateRegIdUsedQuery);
            updateRegIdUsedPs.setInt(1, regId);
            updateRegIdUsedPs.executeUpdate();
    
            PreparedStatement userPs = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userPs.setString(1, consultant.getUsername());
            userPs.setString(2, consultant.getPassword());
            userPs.setString(3, consultant.getRole());
            userPs.executeUpdate();

            int userId;
            try (ResultSet generatedKeys = userPs.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            PreparedStatement jobSeekerPs = connection.prepareStatement(consultantQuery);
            jobSeekerPs.setString(1, consultant.getName());
            jobSeekerPs.setString(2, consultant.getEmail());
            jobSeekerPs.setString(3, consultant.getSpecializedJob());
            jobSeekerPs.setString(4, consultant.getSpecializedCountry());
            jobSeekerPs.setInt(5, userId);
            jobSeekerPs.executeUpdate();

            connection.commit();
            jobSeekerPs.close(); 
            userPs.close();
            updateRegIdUsedPs.close();
            checkRegIdPs.close();
            
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
	}



	@Override
	public boolean deleteConsultant(int consultantId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String query = "DELETE FROM user WHERE user_id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, consultantId);
		
		boolean result = false;
		if(ps.executeUpdate() > 0) {
			result = true;
		}
		
		ps.close();
		connection.close();
		
		return result;
	}

}
