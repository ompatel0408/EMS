package com.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import com.bean.EMSLoginBean;
import com.dao.EMSLoginDao;
import com.service.EMSLoginServices;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Multipart;



public class SendMail {
	
	public static void sendPassword(String email, String name,String token) {

		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		final String from = "royalclubjan@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		
		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, "efwzsnwttkfumekf");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(LocalDate.now()+" Login Password From EMS Projects PVT LTD");

			// Now set the actual message
			message.setText(" Dear, ".concat(name).concat("\n Your today's Login Password is : \n\n")+token+"\n\n Please Don't share with anyone.");
			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException m) {

			m.printStackTrace();
		}
		

	}
	
	public static void mailTokenEveryDay() {
		
		ArrayList<EMSLoginBean> ar = new ArrayList<EMSLoginBean>();
		for(EMSLoginBean ELB:EMSLoginDao.getInstance().getData()) {
			EMSLoginBean Token  = EMSLoginServices.generateJWTToken(ELB.getEmail(),ELB.getRole());
			ar.add(new EMSLoginBean(ELB.getEmail(),Token.getToken(), Token.getSecretKey()));
			SendMail.sendPassword(ELB.getEmail(), ELB.getName(),Token.getToken());
		}
		if(EMSLoginDao.getInstance().updateDataToDatabase(ar)) {
			System.out.println("Updated successfully!");
		}else {
			System.out.println("Updated not successfully!");
		}
	}
	
	public static void sendErrorLogs(String email,String ErrorLogs) {

		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		final String from = "royalclubjan@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		
		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, "efwzsnwttkfumekf");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(LocalDate.now()+" Login Password From EMS Projects PVT LTD");

			// Now set the actual message
			message.setText(ErrorLogs);
			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException m) {
			m.printStackTrace();
		}
	}
	
	public static boolean sendPurchaseOrder(String PATH,String vendorMail) {

		// Recipient's email ID needs to be mentioned.
		String to = vendorMail;

		// Sender's email ID needs to be mentioned
		final String from = "royalclubjan@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		
		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, "efwzsnwttkfumekf");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Purchase Order From EMS Projects PVT LTD.");

			MimeBodyPart attachmentPart = new MimeBodyPart();

			// Set the data source for the attachment
			DataSource source = new FileDataSource(PATH);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName("PurchaseOrder.pdf");

			// Create a multipart message to combine text and attachment
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(attachmentPart);

			// Add the multipart message to the main message
			message.setContent(multipart);
			
			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
			
			return true;
		} catch (MessagingException m) {
			m.printStackTrace();
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		SendMail.mailTokenEveryDay();
	}
	
	
	
}
