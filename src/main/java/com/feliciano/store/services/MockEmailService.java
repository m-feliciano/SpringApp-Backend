package com.feliciano.store.services;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Slf4j
@Service
public class MockEmailService extends AbstractEmailService {

    public MockEmailService(@Autowired TemplateEngine templateEngine,
                            @Autowired(required = false) JavaMailSender jmSender) {
        super(templateEngine, jmSender);
    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Simulando envio de email...");
        log.info(msg.toString());
        log.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Simulando envio email html...");
        log.info(msg.toString());
        log.info("Email html sent");
    }

}
