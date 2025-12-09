package com.feliciano.store.dto;

import com.feliciano.store.resources.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {
	private Integer id;
	private String name;
	private Double price;

	public ProductDTO(Product obj) {
		id = obj.getId();
		name = obj.getName();
		price = obj.getPrice();
	}
}
