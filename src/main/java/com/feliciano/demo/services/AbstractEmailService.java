package com.feliciano.demo.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.resources.domain.Order;

//Template method pattern
public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired(required = false)
	private JavaMailSender jmSender;

	@Override
	public void sendOrderConfirmationEmail(Order obj) {
		SimpleMailMessage sm = SimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage SimpleMailMessageFromPedido(Order obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Code: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	protected String htmlFromTemplatePedido(Order obj) {
		Context context = new Context();
		context.setVariable("order", obj);
		return templateEngine.process("emails/orderConfirmation", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Order obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	private MimeMessage prepareMimeMessageFromPedido(Order obj) throws MessagingException {
		MimeMessage mimeMessage = jmSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true); // true: informs that this is a html
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed! Code: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}

	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		sendEmail(sm);
	}

	private SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New password request!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New password:	" + newPass);
		return sm;
	}
}
