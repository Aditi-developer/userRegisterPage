package com.example.project.serviceImpl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.project.models.UserModel;
import com.example.project.service.EmailService;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Template;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private FreeMarkerConfigurer freemarkerConfiguration;

	@Override
	public void sendSimpleMessage(String to, UserModel user) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();

		if (null != to && null != user) {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject("New User Registered.");
			mimeMessageHelper.setFrom("agrawalad7@gmail.com");
			mimeMessageHelper.setTo(to);

			Map<String, Object> templateInput = new HashMap<>();
			templateInput.put("username", user.username);
			templateInput.put("password", user.password);
			if (null != user.mobileNumber && !user.mobileNumber.isEmpty()) {
				templateInput.put("mobile", user.mobileNumber);
			} else {
				templateInput.put("mobile", "");
			}
			templateInput.put("email", user.email);
			mimeMessageHelper.setText(templateToString("user.ftl", templateInput), true);

			emailSender.send(mimeMessageHelper.getMimeMessage());

		}
	}

	public String templateToString(String templateName, Map<String, Object> templateInput) {
		if (templateName == null || templateName.isEmpty()) {
			return "No Template found";
		}
		Template template;
		StringWriter result = new StringWriter();
		try {
			FileTemplateLoader templateLoader = null;
			try {
				template = freemarkerConfiguration.getConfiguration().getTemplate(templateName);
				template.process(templateInput, result);
			} catch (Exception e) {

				System.out.print("error occured in templateToString method::" + e);
			}
		} catch (Exception e) {
			System.out.print("error occured in templateToString method::" + e);
		} finally {
			try {
				result.close();
			} catch (IOException e) {
				System.out.print("error occured in templateToString method::" + e);
			}
		}
		return result.toString();
	}
}