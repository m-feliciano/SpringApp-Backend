package com.feliciano.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

}
