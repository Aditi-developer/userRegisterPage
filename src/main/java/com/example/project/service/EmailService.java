package com.example.project.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.example.project.models.UserModel;

@Service
public interface EmailService {


	void sendSimpleMessage(String string, UserModel us) throws MessagingException;

}
