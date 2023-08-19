package com.ruhaim.appointment.model;

public class AppointmentDetails {
    private int appointmentId;
    private String date;
    private String time;
    private String status;
    private String consultantName; 
    private String jobSeekerName; 

    public AppointmentDetails(int appointmentId, String date, String time, String status, String consultantName, String jobSeekerName) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.consultantName = consultantName;
        this.jobSeekerName = jobSeekerName;
    }

	public AppointmentDetails() {
		// TODO Auto-generated constructor stub
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

	public String getConsultantName() {
		return consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getJobSeekerName() {
		return jobSeekerName;
	}

	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}

    // Getters and setters for all fields...
}
