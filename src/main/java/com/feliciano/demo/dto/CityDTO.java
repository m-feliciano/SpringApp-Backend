package com.feliciano.demo.dto;

import java.io.Serializable;

import com.feliciano.demo.resources.domain.City;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public CityDTO(City obj) {
		id = obj.getId();
		name = obj.getName();
	}

}
