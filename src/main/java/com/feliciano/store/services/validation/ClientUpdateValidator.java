package com.feliciano.store.services.validation;

import com.feliciano.store.dto.ClientDTO;
import com.feliciano.store.repositories.ClientRepository;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private ClientRepository repo;
    @Autowired
    private HttpServletRequest req;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        Object urlId = req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        List<FieldMessage> list = new ArrayList<>();
        Client aux = repo.findByEmail(objDto.getEmail());

        if (aux != null && !aux.getId().equals(urlId)) {
            list.add(new FieldMessage("email", "E-mail already exists"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}