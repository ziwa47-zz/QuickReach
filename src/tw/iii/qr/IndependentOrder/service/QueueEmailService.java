package tw.iii.qr.IndependentOrder.service;

import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class QueueEmailService {

	private String registerVerifyMailContent;

	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public String getRegisterVerifyMailContent() {
		return registerVerifyMailContent;
	}

	public void setRegisterVerifyMailContent(String registerVerifyMailContent) {
		this.registerVerifyMailContent = registerVerifyMailContent;
	}

	public static final String DEFAULT_SMTP = "DEFAULT";

	private JavaMailSender mailSender;
	 
	public JavaMailSender getMailSender() {
		return mailSender;
	}
 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 

	public void sendMail(String from, String to, String subject, String msg) {
		MimeMessage message = mailSender.createMimeMessage();
 
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg);
 
		}
		catch (MessagingException e) {
			throw new MailParseException(e);
		}
 
		mailSender.send(message);
	}
}
