package com.feliciano.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feliciano.demo.resources.domain.BoletoPayment;
import com.feliciano.demo.resources.domain.CardPayment;

@Configuration
public class JacksonConfig {
	/*
	 * https://stackoverflow.com/questions/41452598/overcome
	 * -can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
	 */
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder() {
			@Override
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(BoletoPayment.class);
				objectMapper.registerSubtypes(CardPayment.class);
				super.configure(objectMapper);
			}

		};
	}

}
