package com.ruhaim.appointment.dao;
import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.model.Consultant;


public interface ConsultantManager {
	public boolean registerConsultantWithId(int regId) throws ClassNotFoundException, SQLException;
	public boolean registerConsultant(Consultant consultant, int regId) throws SQLException, ClassNotFoundException;
	public boolean deleteConsultant(int consultantId) throws SQLException, ClassNotFoundException;
	List<Consultant> getAllConsultants(String job, String country) throws SQLException, ClassNotFoundException;
	
}
