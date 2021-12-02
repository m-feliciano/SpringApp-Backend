package com.feliciano.demo.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.feliciano.demo.services.DBService;
import com.feliciano.demo.services.EmailService;
import com.feliciano.demo.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String profile;

	@Bean
	public boolean instanciateDatabase() throws ParseException {

		if (!"create".equals(profile)) {
			return false;
		}
		dbService.instanciateTestDatabase();

		return true;
	}
	
	@Bean
	public EmailService emailSevice() {
		return new SmtpEmailService();
	}

}
