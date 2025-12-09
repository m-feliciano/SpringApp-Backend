package com.feliciano.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmailDTO implements Serializable {

	@NotEmpty(message = "Mandatory completion")
	@Email(message = "invalid email")
	private String email;
}
