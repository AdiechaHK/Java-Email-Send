package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import action.Mail;

public class MainFrame extends JFrame implements ActionListener{

	/**
	 * @author hk
	 */
	private static final long serialVersionUID = 1L;

	private JButton sendMail;
	private JTextField mailTo;
	private JTextField mailFrom;
	private JTextField msgSub;
	private JPasswordField pass;
	private JTextArea msgBody;
	private JLabel statusText;
	
	public MainFrame(){
		super();
		
		// FRAME SPECIFICATIONS
		this.setSize(310, 500);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// MAIL FROM
		mailFrom = new JTextField();
		mailFrom.setBounds(90,10,200,30);
		JLabel labelFrm = new JLabel("From:");
		labelFrm.setBounds(20,10,80,30);
		this.add(labelFrm);
		this.add(mailFrom);
		
		// MAIL PASSWORD
		pass = new JPasswordField();
		pass.setBounds(90,40,200,30);
		JLabel labelPsw = new JLabel("Password:");
		labelPsw.setBounds(20,40,80,30);
		this.add(labelPsw);
		this.add(pass);

		// MAIL TO
		mailTo = new JTextField();
		mailTo.setBounds(90,70,200,30);
		JLabel labelTo = new JLabel("To:");
		labelTo.setBounds(20,70,80,30);
		this.add(labelTo);
		this.add(mailTo);

		// MAIL SUBJECT
		msgSub = new JTextField();
		msgSub.setBounds(90,100,200,30);
		JLabel labelSub = new JLabel("Subject:");
		labelSub.setBounds(20,100,80,30);
		this.add(labelSub);
		this.add(msgSub);

		// MAIL MESSAGE
		msgBody = new JTextArea();
		msgBody.setBounds(20,160,265,200);
		JLabel labelMsg = new JLabel("Message:");
		labelMsg.setBounds(20,130,80,30);
		this.add(labelMsg);
		this.add(msgBody);

		// MAIL STATUS TEXT
		statusText = new JLabel("Ready...");
		statusText.setBounds(20,370,100,30);
		this.add(statusText);
		
		// MAIL SEND BUTTON
		sendMail = new JButton("Send Mail");
		sendMail.setBounds(190,370,100,30);
		sendMail.addActionListener(this);
		this.add(sendMail);

		
	}

	public void setStatus(String text) {
   	 	statusText.setText(text);
		sendMail.setEnabled(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// For text fields => method => getText();
		// For password fields => method => getPassword();
		
		statusText.setText("Please Wait..");
		  sendMail.setEnabled(false);

		  final String user= mailFrom.getText();
		  final String password=new String(pass.getPassword());
		  String subject = msgSub.getText();
		  String body = msgBody.getText();
		  String to = mailTo.getText();

		   Mail mail = new Mail(to, user, password, subject, body, this);
		   mail.start();
	}	
}
