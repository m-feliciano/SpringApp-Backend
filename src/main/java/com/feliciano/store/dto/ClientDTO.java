package com.feliciano.store.dto;

import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.services.validation.ClientUpdate;
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
@NoArgsConstructor
@AllArgsConstructor
@ClientUpdate
public class ClientDTO implements Serializable {
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
