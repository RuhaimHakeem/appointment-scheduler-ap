package com.ruhaim.appointment.dao.dbutils;

public class DbDriverManagerFactory {

public DbDriverManager getDbDriver(String databaseType) {
		
		if(databaseType.equals("MySQL")) {
			return new DbDriverManagerMysqlImpl();
		}
		else {
			return null;
		}
	}

}
