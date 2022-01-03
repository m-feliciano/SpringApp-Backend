package com.feliciano.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Mandatory completion")
	@Email(message = "inválid email")
	private String email;
}
