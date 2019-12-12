package com.roytuts.spring.email.multiple.attachments;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roytuts.spring.email.multiple.attachments.sender.EmailSender;

@SpringBootApplication(scanBasePackages = "com.roytuts.spring.email.multiple.attachments")
public class SpringEmailMultipleAttachmentsApp implements CommandLineRunner {

	@Autowired
	private EmailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailMultipleAttachmentsApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		File attachment1 = new File("employee_report.csv");
		File attachment2 = new File("sample.pdf");

		File[] attachments = new File[] { attachment1, attachment2 };

		emailSender.sendEmailMultiAttachment("How are you ?",
				"Hello, <i>How are you ?</i> <br/> Please open attachments to view the files.", "gmail@gmail.com",
				"gmail@gmail.com", attachments, true);

	}

}
