package ufc.quixada.npi.gpa.service.impl;


import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Named
public class EmailService {
	
	@Inject
	private MailSender mailSender;

	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
}
