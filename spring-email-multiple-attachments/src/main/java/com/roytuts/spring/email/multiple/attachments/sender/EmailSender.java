package com.roytuts.spring.email.multiple.attachments.sender;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmailMultiAttachment(final String subject, final String message, final String fromEmailAddress,
			final String toEmailAddress, final File[] attachments, final boolean isHtmlMail) {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom(fromEmailAddress);
			helper.setTo(toEmailAddress);
			helper.setSubject(subject);

			if (isHtmlMail) {
				helper.setText("<html><body>" + message + "</html></body>", true);
			} else {
				helper.setText(message);
			}

			// add the file attachment
			for (File file : attachments) {
				FileSystemResource fr = new FileSystemResource(file);
				helper.addAttachment(file.getName(), fr);
			}

			mailSender.send(mimeMessage);

			System.out.println("Email sending with multiple attachments complete.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
