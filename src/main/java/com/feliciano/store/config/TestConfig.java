package com.feliciano.store.config;

import com.feliciano.store.services.DBService;
import com.feliciano.store.services.EmailService;
import com.feliciano.store.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender jmSender;

    @Autowired(required = false)
    public TestConfig(DBService dbService,
                      TemplateEngine templateEngine,
                      JavaMailSender jmSender) {
        this.dbService = dbService;
        this.templateEngine = templateEngine;
        this.jmSender = jmSender;
    }

    @Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
        return new MockEmailService(templateEngine, jmSender);
	}

}
