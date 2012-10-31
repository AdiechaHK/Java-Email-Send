package action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import frame.MainFrame;

public class Mail extends Thread {
	  MainFrame parant;
	  final String user;
	  final String password;//=new String(pass.getPassword());
	  String subject;// = msgSub.getText();
	  String body;// = msgBody.getText();
	  String to;// = mailTo.getText();

	public Mail(String _to,String  from,String  pass,String  sub,String  msg, MainFrame p){
		super();
		parant = p;
		user = from;
		password = pass;
		subject = sub;
		body = msg;
		to = _to;
	}
	
	public void run() {
		  String host="smtp.gmail.com";
//		  final String user= mailFrom.getText();
//		  final String password=new String(pass.getPassword());
//		  String subject = msgSub.getText();
//		  String body = msgBody.getText();
//		  String to = mailTo.getText();

		   //Get the session object
		   Properties props = new Properties();
		   props.put("mail.smtp.host",host);
		   props.put("mail.smtp.auth", "true");
		   props.setProperty("mail.smtp.port", "" + 587);
		   props.setProperty("mail.smtp.starttls.enable", "true");
		   
		   Session session = Session.getDefaultInstance(props,
		    new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,password);
		      }
		    });

		   //Compose the message
		    try {
		     MimeMessage message = new MimeMessage(session);
		     message.setFrom(new InternetAddress(user));
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		     message.setSubject(subject);
		     message.setText(body);
		     
		    //send the message
		     Transport.send(message);

		     System.out.println("message sent successfully...");
	    	 parant.setStatus("Send Successfully.");

		     } catch (MessagingException e2) {
		    	 e2.printStackTrace();
		    	 parant.setStatus("Send Fails.");
		     }

	}

}
