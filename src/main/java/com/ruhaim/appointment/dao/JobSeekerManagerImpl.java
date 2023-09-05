package com.ruhaim.appointment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import com.ruhaim.appointment.dao.dbutils.DbDriverManager;
import com.ruhaim.appointment.dao.dbutils.DbDriverManagerFactory;
import com.ruhaim.appointment.model.JobSeeker;

public class JobSeekerManagerImpl implements JobSeekerManager {
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");
		
		return driverManager.getConnection();
	}

	public JobSeekerManagerImpl() {

	}
	
	@Override
	public boolean registerJobSeeker(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {

		Connection connection = getConnection();
		
		String userQuery = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        String jobSeekerQuery = "INSERT INTO job_seeker (name, email, user_id) VALUES (?, ?, ?)";
        
        try {
            connection.setAutoCommit(false);

    
            PreparedStatement userPs = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userPs.setString(1, jobSeeker.getUsername());
            userPs.setString(2, jobSeeker.getPassword());
            userPs.setString(3, jobSeeker.getRole());
            userPs.executeUpdate();

            int userId;
            try (ResultSet generatedKeys = userPs.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            PreparedStatement jobSeekerPs = connection.prepareStatement(jobSeekerQuery);
            jobSeekerPs.setString(1, jobSeeker.getName());
            jobSeekerPs.setString(2, jobSeeker.getEmail());
            jobSeekerPs.setInt(3, userId);
            jobSeekerPs.executeUpdate();

            connection.commit();
            jobSeekerPs.close();
            userPs.close();
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
	public List<JobSeeker> getAllJobSeekers() throws SQLException, ClassNotFoundException {
		 Connection connection = getConnection();
		    
		    String query = "SELECT * FROM job_seeker";
		    Statement st = connection.createStatement();
		    
		    List<JobSeeker> jobSeekerList = new ArrayList<>();
		    
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()) {
		        JobSeeker jobSeeker = new JobSeeker();
		        jobSeeker.setJobSeekerId(rs.getInt("job_seeker_id"));
		        jobSeeker.setName(rs.getString("name"));
		        jobSeeker.setEmail(rs.getString("email"));
		        jobSeeker.setUserId(rs.getInt("user_id"));
		        
		        jobSeekerList.add(jobSeeker);
		    }
		    
		    st.close();
		    connection.close();
		    
		    return jobSeekerList;
	}
	
	@Override
	public JobSeeker getJobSeekerById(int userId) throws SQLException, ClassNotFoundException {
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



	@Override
	public boolean deleteJobSeeker(int jobSeekerId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String query = "DELETE FROM user WHERE user_id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, jobSeekerId);
		
		boolean result = false;
		if(ps.executeUpdate() > 0) {
			result = true;
		}
		
		ps.close();
		connection.close();
		
		return result;
	}

}
