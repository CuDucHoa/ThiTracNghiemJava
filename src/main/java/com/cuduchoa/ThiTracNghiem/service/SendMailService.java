package com.cuduchoa.ThiTracNghiem.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendMailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String toEmail, String body, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ntantu68@gmail.com");
		message.setTo(toEmail);
		message.setText("Ma thay doi mat khau cua ban la : " + text);
		message.setSubject("Thay doi mat khau");
		mailSender.send(message);
	}
}
