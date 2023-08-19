package com.ruhaim.appointment.model;

public class AvailabilityTime extends Consultant {
	
	private String date;
	private String time;
	private int consultantId;
	
	public AvailabilityTime() {
		// TODO Auto-generated constructor stub
	}


	public AvailabilityTime(String date, String time, int consultantId) {
		this.date = date;
		this.time = time;
		this.consultantId = consultantId;
	}


	public String getDate() {
		return date;
	}


	public String setDate(String date) {
		return this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public int getConsultantId() {
		return consultantId;
	}


	public void setConsultantId(int consultantId) {
		this.consultantId = consultantId;
	}

}
