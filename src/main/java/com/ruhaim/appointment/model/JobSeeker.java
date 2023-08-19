package com.ruhaim.appointment.model;

public class JobSeeker extends User {
	
	private int jobSeekerId;
	private String name;
	private String email;
	private int userId;

	public JobSeeker() {
		// TODO Auto-generated constructor stub
	}
	
	
	public JobSeeker(int jobSeekerId, String name, String email, int userId) {
		this.jobSeekerId = jobSeekerId;
		this.name = name;
		this.email = email;
		this.userId =userId;
	}


	public int getJobSeekerId() {
		return jobSeekerId;
	}


	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId=jobSeekerId;
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


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


}
