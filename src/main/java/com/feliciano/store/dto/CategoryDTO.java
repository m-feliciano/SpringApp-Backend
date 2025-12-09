package com.feliciano.store.dto;

import com.feliciano.store.resources.domain.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {
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
