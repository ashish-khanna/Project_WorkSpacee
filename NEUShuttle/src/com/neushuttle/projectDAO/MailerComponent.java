package com.neushuttle.projectDAO;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailerComponent {

	public void sendMail(String receiverMail, String subjectLine, String msg) {
		 
		final String username = "nu.transport.service@gmail.com";
		final String password = "nu@12345";
 
/*		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });*/
 
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nu.transport.service@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiverMail));
			message.setSubject(subjectLine);
			message.setText(msg);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
