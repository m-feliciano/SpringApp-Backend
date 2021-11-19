package com.feliciano.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feliciano.demo.resources.domain.PagamentoComBoleto;
import com.feliciano.demo.resources.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig {
	/*
	 * https://stackoverflow.com/questions/41452598/overcome
	 * -can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
	 */
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}

}