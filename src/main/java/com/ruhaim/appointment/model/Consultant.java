package com.ruhaim.appointment.model;

public class Consultant extends User {
	
	private int consultantId;
	private String name;
	private String email;
	private String specializedJob;
	private String specializedCountry;
	private int userId;
	

	public Consultant(int consultantId, String name, String email, String specializedJob, String specializedCountry, int userId ) {
		this.consultantId = consultantId;
		this.name = name;
		this.email = email;
		this.specializedJob = specializedJob;
		this.specializedCountry = specializedCountry;
		this.userId =userId;
	}


	public Consultant() {
		// TODO Auto-generated constructor stub
	}


	public int getConsultantId() {
		return consultantId;
	}


	public void setConsultantId(int consultantId) {
		this.consultantId = consultantId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSpecializedJob() {
		return specializedJob;
	}


	public void setSpecializedJob(String specializedJob) {
		this.specializedJob = specializedJob;
	}


	public String getSpecializedCountry() {
		return specializedCountry;
	}


	public void setSpecializedCountry(String specializedCountry) {
		this.specializedCountry = specializedCountry;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
