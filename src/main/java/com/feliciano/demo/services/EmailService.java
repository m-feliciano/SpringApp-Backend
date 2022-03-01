package com.feliciano.demo.services;

import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.resources.domain.Order;
import org.springframework.mail.SimpleMailMessage;
import javax.mail.internet.MimeMessage;


public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	void sendEmail(SimpleMailMessage msg);
	void sendOrderConfirmationHtmlEmail(Order obj);
	void sendHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Client client, String newPass);

}
