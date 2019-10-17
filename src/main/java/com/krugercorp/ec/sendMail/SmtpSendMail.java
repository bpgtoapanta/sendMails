package com.krugercorp.ec.sendMail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpSendMail {
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	public static void generateAndSendEmail(String port, String [] to, String[] cc, String subject, String body, String user, String password) throws AddressException, MessagingException {
		// Step1
		System.out.println("\n 1st ===> Setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", port);
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> Get Mail Session..");
		
		try {
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			if (to != null && to.length > 0) {
				for (String mailTo : to) {
					generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
				}
			}
			if (cc != null && cc.length > 0) {
				for (String mailCc : cc) {
					generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(mailCc));
				}
			}
			generateMailMessage.setSubject(subject);
			String emailBody = body;
			generateMailMessage.setContent(emailBody, "text/html");
			System.out.println("Mail Session has been created successfully..");
	 
			// Step3
			System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");
	 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("smtp.gmail.com", user, password);
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			System.out.println("\n\n 4th ===> Mail Send!!!");
			transport.close();
		} catch (Exception e) {
			System.out.println("Exception here "+ e);
		}
	}
}
