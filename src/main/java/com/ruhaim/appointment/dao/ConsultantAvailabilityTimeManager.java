package com.ruhaim.appointment.dao;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.AvailabilityTime;


public interface ConsultantAvailabilityTimeManager {
	public boolean addAvailabilityTime(AvailabilityTime availabilityTime, int userId) throws ClassNotFoundException, SQLException;
	public List<AvailabilityTime> getAllAvailabiltyTimes() throws SQLException, ClassNotFoundException;
	public boolean deleteAvailabilityTime(int availabilityTimeId) throws SQLException, ClassNotFoundException;
	public List<AvailabilityTime> getAvailabiltyTimesByJobAndCountry(String job, String country) throws SQLException, ClassNotFoundException;
	List<AvailabilityTime> getAvailabiltyTimesByConsultant(int userId) throws SQLException, ClassNotFoundException;
}
