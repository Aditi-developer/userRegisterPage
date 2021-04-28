package com.example.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.project.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(
	  String to, String subject, String text) {
	    SimpleMailMessage message = new SimpleMailMessage(); 
	    message.setFrom("agrawalad7@gmail.com");
	    message.setTo(to); 
	    message.setSubject(subject); 
	    message.setText(text);
	    emailSender.send(message);
	}
}
