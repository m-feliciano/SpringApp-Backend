package com.feliciano.demo.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.feliciano.demo.resources.domain.Pedido;

//Template method pattern
public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;

	public void sendOrderConfirmationEmail(Pedido obj){
		SimpleMailMessage sm =  SimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage SimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm =  new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: "+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
