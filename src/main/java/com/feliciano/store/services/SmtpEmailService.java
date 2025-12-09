package com.feliciano.store.services;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Slf4j
@Service
public class SmtpEmailService extends AbstractEmailService {

    private MailSender mailSender;
    private JavaMailSender jmaSender;

    @Autowired(required = false)
    public SmtpEmailService(TemplateEngine templateEngine,
                            MailSender mailSender,
                            JavaMailSender jmaSender) {
        super(templateEngine, jmaSender);
        this.mailSender = mailSender;
        this.jmaSender = jmaSender;
    }

    @Autowired(required = false)
    public SmtpEmailService(TemplateEngine templateEngine, JavaMailSender jmSender) {
        super(templateEngine, jmSender);
    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Sending email...");
        mailSender.send(msg);
        log.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Sending email...");
        jmaSender.send(msg);
        log.info("Email sent");
    }
}
