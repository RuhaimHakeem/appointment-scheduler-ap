package com.ruhaim.appointment.service;
import java.sql.SQLException;
import java.util.List;
import com.ruhaim.appointment.dao.ConsultantAvailabilityTimeManager;
import com.ruhaim.appointment.dao.ConsultantAvailabilityTimeManagerImpl;
import com.ruhaim.appointment.model.AvailabilityTime;

public class ConsultantAvailabilityTimeService {
	
	private static ConsultantAvailabilityTimeService consultantAvailabilityTimeServiceObj;
	
public synchronized static ConsultantAvailabilityTimeService getConsultantAvailabilityTimeService() {
		
		if(consultantAvailabilityTimeServiceObj == null) {
			consultantAvailabilityTimeServiceObj = new ConsultantAvailabilityTimeService();
		}
		
		return consultantAvailabilityTimeServiceObj;
	}

	private ConsultantAvailabilityTimeManager getConsultantAvailabilityTimeDao() {

		return new ConsultantAvailabilityTimeManagerImpl();
	
	}
	
	public boolean addAvailabilityTime(AvailabilityTime availabilityTime, int userId) throws ClassNotFoundException, SQLException {
		return getConsultantAvailabilityTimeDao().addAvailabilityTime(availabilityTime, userId);
		
	}
	
	public List<AvailabilityTime> getAllAvailabiltyTimes() throws ClassNotFoundException, SQLException {
		return getConsultantAvailabilityTimeDao().getAllAvailabiltyTimes();
		
	}
	
	
	public boolean deleteAvailabilityTime(int availabilityTimeId) throws ClassNotFoundException, SQLException {
		return getConsultantAvailabilityTimeDao().deleteAvailabilityTime(availabilityTimeId);
	
	}
	
	public List<AvailabilityTime> getAvailabiltyTimesByJobAndCountry(String job, String country) throws ClassNotFoundException, SQLException {
		return getConsultantAvailabilityTimeDao().getAvailabiltyTimesByJobAndCountry(job, country);
	
	}
	
	public List<AvailabilityTime> getAvailabiltyTimesByConsultant(int userId) throws ClassNotFoundException, SQLException {
		return getConsultantAvailabilityTimeDao().getAvailabiltyTimesByConsultant(userId);
	
	}

}


