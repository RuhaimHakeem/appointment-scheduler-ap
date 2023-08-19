package com.ruhaim.appointment.model;


public class Appointment {
	
	private int appointmentId;
	private String date;
	private String time;
	private String status;
	private int consultantId;
	private int jobSeekerId;
	

	
	public Appointment() {

	}

	public Appointment(int appointmentId, String date, String time,  String status, int consultantId, int jobSeekerId) {
		this.appointmentId = appointmentId;
		this.date = date;
		this.time = time;
		this.status = status;
		this.consultantId = consultantId;
		this.jobSeekerId =jobSeekerId;

	}
	

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(int consultantId) {
		this.consultantId = consultantId;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

}
