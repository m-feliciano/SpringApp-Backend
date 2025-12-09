package com.feliciano.store.services;

import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.domain.Order;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	void sendEmail(SimpleMailMessage msg);
	void sendOrderConfirmationHtmlEmail(Order obj);
	void sendHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Client client, String newPass);

}
