package mailer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	String user;
	String password;
	String subject;
	String body;
	String to;

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public boolean send() {
		boolean state;
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "" + 587);
		props.setProperty("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setText(body);

			// send the message
			Transport.send(message);

			System.out.println("message sent successfully...");
			state = true;

		} catch (MessagingException e2) {
			e2.printStackTrace();
			state = false;
		}
		return state;
	}
}
