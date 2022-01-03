package com.feliciano.demo.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.resources.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Order obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Client client, String newPass);

}
