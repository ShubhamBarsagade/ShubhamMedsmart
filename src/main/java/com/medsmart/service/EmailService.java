package com.medsmart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendMail(String subject, String message,String to) {
		boolean f= false;
		String from="sbarsagade07@gmail.com";
		
		//variable for gmail
		String host ="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES"+ properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//step1 to get session object ..
		
		Session session=Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("sbarsagade07@gmail.com", "S11031995$s");
			}
		});
		
		session.setDebug(true);
		
		//step2 compose the massage [text,multi media]
		MimeMessage m = new MimeMessage(session);
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			//m.setText(message);
			m.setContent(message,"text/html");
			
			//send
			
			//stepm send the message using Transport class
			Transport.send(m);
			
			System.out.println("Sent Success.............");
			
			
			f=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	

}
