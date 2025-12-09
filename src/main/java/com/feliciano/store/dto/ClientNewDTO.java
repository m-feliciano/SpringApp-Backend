package com.feliciano.store.dto;

import com.feliciano.store.services.validation.ClientInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ClientInsert
public class ClientNewDTO implements Serializable {
	@NotEmpty(message = "Mandatory completion")
	@Length(min = 5, max = 100, message = "The client '${validatedValue}' must be between {min} and {max} characters")
	private String name;
	@NotEmpty(message = "Mandatory completion")
	@Email(message = "Invalid email")
	private String email;
	@NotEmpty(message = "Mandatory completion")
	private String cpfOrCnpj;
	private Integer type;
	@NotEmpty(message = "Mandatory completion")
	private String street;
	@NotEmpty(message = "Mandatory completion")
	private String number;
	private String complement;
	@NotEmpty(message = "Mandatory completion")
	private String district;
	private String cep;
	@NotEmpty(message = "Mandatory completion")
	private String phone1;
	private String phone2;

	private Integer cityId;

	@NotEmpty(message = "Mandatory completion")
	private String password;

}
