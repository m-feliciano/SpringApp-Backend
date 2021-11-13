package com.feliciano.demo.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.feliciano.demo.dto.ClienteDTO;
import com.feliciano.demo.repositories.ClienteRepository;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private HttpServletRequest req;
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		Object urlId = req.getAttribute(
				HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(urlId)) {
			list.add(new FieldMessage("email", "E-mail ja existente!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}