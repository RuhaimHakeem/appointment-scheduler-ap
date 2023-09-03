package com.ruhaim.appointment.model;

public class Email {
	
	private String jobSeekerName;
	private String consultantName;
	private String recipientName;
	private String recipientMail;
	private String date;
	private String time;
	
	public Email(String jobSeekerName, String consultantName, String recipientName,String recipientMail, String date, String time) {
		this.jobSeekerName = jobSeekerName;
		this.consultantName = consultantName;
		this.recipientName = recipientName;
		this.recipientMail = recipientMail;
		this.date = date;
		this.time = time;
		
	}

	public Email() {
		// TODO Auto-generated constructor stub
	}

	public String getJobSeekerName() {
		return jobSeekerName;
	}

	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}

	public String getConsultantName() {
		return consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientMail() {
		return recipientMail;
	}

	public void setRecipientMail(String recipientMail) {
		this.recipientMail = recipientMail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
