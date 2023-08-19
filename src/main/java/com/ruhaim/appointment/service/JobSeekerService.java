package com.ruhaim.appointment.service;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.dao.JobSeekerManager;
import com.ruhaim.appointment.dao.JobSeekerManagerImpl;
import com.ruhaim.appointment.model.JobSeeker;


public class JobSeekerService {
	
	private static JobSeekerService jobSeekerServiceObj;

	public JobSeekerService() {
		
	}
	
	public synchronized static JobSeekerService getJobSeekerService() {
		
		if(jobSeekerServiceObj == null) {
			jobSeekerServiceObj = new JobSeekerService();
		}
		
		return jobSeekerServiceObj;
	}
	
	private JobSeekerManager getJobSeekerManagerDao() {
		
		return new JobSeekerManagerImpl();
		
	}
	
	public boolean regiserJobSeeker(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
		return getJobSeekerManagerDao().registerJobSeeker(jobSeeker);
	}
	
	public List<JobSeeker> getAllJobSeekers() throws ClassNotFoundException, SQLException {
		return getJobSeekerManagerDao().getAllJobSeekers();
	}
	
	public boolean deleteJobSeeker(int jobSeekerid) throws ClassNotFoundException, SQLException {
		return getJobSeekerManagerDao().deleteJobSeeker(jobSeekerid);
	}

	
}