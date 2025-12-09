package com.feliciano.store.services.validation;

import com.feliciano.store.dto.ClientNewDTO;
import com.feliciano.store.repositories.ClientRepository;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.domain.enums.ClientType;
import com.feliciano.store.resources.exceptions.FieldMessage;
import com.feliciano.store.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	private ClientRepository repo;

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(ClientType.PERSON.getCod()) && !BR.isValidSsn(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF invalid"));
		}

		if (objDto.getType().equals(ClientType.ENTITY.getCod()) && !BR.isValidSsn(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ invalid"));
		}

		Client aux = repo.findByEmail(objDto.getEmail());

		if (aux != null) {
			list.add(new FieldMessage("email", "E-mail already exists!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}