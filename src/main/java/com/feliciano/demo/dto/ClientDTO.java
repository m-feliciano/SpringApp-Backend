package com.feliciano.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.services.validation.ClientUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Mandatory completion")
	@Length(min = 5, max = 100, message = "The client '${validatedValue}' must be between {min} and {max} characters")
	private String name;

	@NotEmpty(message = "Mandatory completion")
	@Email(message = "Invalid email")
	private String email;

	public ClientDTO(Client obj) {
		super();
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

}
