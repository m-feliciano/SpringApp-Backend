package com.feliciano.demo.services;

import org.springframework.mail.SimpleMailMessage;

import com.feliciano.demo.resources.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
