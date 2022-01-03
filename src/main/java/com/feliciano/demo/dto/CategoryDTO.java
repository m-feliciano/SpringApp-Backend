package com.feliciano.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.feliciano.demo.resources.domain.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;

	@NotEmpty(message = "Mandatory completion")
	@Length(min = 5, max = 14, message = "The category '${validatedValue}' must be between {min} and {max} characters")
	private String name;

	public CategoryDTO(Category obj) {
		super();
		id = obj.getId();
		name = obj.getName();
	}

}
