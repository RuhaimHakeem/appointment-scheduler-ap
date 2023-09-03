package com.ruhaim.appointment.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ruhaim.appointment.model.Email;

public class EmailService {
	
	public void send(Email email) throws MessagingException
	{
	

		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myEmail = "thejobscmb@gmail.com";
		String password = "mxscmwrhchtuwnwv";
		String recipientEmail = email.getRecipientMail();
		String consultantName = email.getConsultantName();
		String jobSeekerName = email.getJobSeekerName();
		String recipientName = email.getRecipientName();
		String date = email.getDate();
		String time = email.getTime();
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(myEmail, password);
			}
		});
		
		
		Message message = prepareMessage(session, myEmail, recipientEmail, jobSeekerName, consultantName, recipientName, date, time);
		
		Transport.send(message);
	}
	
	public static Message prepareMessage(Session session, String myEmail, String recipientEmail,  String jobSeekerName, String consultantName, String recipientName, String date, String time)
	{
		String emailText = "Subject: Appointment Scheduled\n"
				+ "\n"
				+ "Dear " + recipientName + ",\n"
				+ "\n"
				+ "We are pleased to inform you that an appointment has been scheduled:\n"
				+ "\n"
				+ "- Job Seeker: " + jobSeekerName + "\n"
				+ "- Consultant: " + consultantName + "\n"
				+ "- Date: " + date + "\n"
				+ "- Time: " + time + "\n"
				+ "\n"
				+ "Thank you for choosing our service, and we look forward to meeting you.\n"
				+ "\n"
				+ "Best regards,\n"
				+ "The Jobs";
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject("Appointment Confirmation");
			message.setText(emailText);
			return message;
		} 
		catch (MessagingException e) 
		{
		
			e.printStackTrace();
		}
		
		return null;
	}
}
