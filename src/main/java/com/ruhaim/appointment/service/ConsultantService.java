package com.ruhaim.appointment.service;

import java.sql.SQLException;
import java.util.List;

import com.ruhaim.appointment.dao.ConsultantManager;
import com.ruhaim.appointment.dao.ConsultantManagerImpl;
import com.ruhaim.appointment.model.Consultant;


public class ConsultantService {
	
	private static ConsultantService consultantServiceObj;

	public ConsultantService() {
	}
	
public synchronized static ConsultantService getConsultantService() {
		
		if(consultantServiceObj == null) {
			consultantServiceObj = new ConsultantService();
		}
		
		return consultantServiceObj;
	}

	private ConsultantManager getConsultantManagerDao() {

		return new ConsultantManagerImpl();
	
	}
	
	public boolean registerConsultantWithId(int regId) throws ClassNotFoundException, SQLException {
		return getConsultantManagerDao().registerConsultantWithId(regId);
	}
	
	public boolean registerConsultant(Consultant consultant, int regId) throws ClassNotFoundException, SQLException {
		return getConsultantManagerDao().registerConsultant(consultant, regId);
	}
	
	
	public boolean deleteConsultant(int consultantId) throws ClassNotFoundException, SQLException {
		return getConsultantManagerDao().deleteConsultant(consultantId);
	}
	
	public List<Consultant> getAllConsultants(String job, String country) throws ClassNotFoundException, SQLException {
		return getConsultantManagerDao().getAllConsultants(job, country);
	}

}
