package com.ruhaim.appointment.dao;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.JobSeeker;

public interface JobSeekerManager {
	
	public boolean registerJobSeeker(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException;
	public boolean deleteJobSeeker(int jobSeekerId) throws SQLException, ClassNotFoundException;
	public List<JobSeeker> getAllJobSeekers() throws SQLException, ClassNotFoundException;
	public JobSeeker getJobSeekerById(int userId) throws SQLException, ClassNotFoundException;
	
}
