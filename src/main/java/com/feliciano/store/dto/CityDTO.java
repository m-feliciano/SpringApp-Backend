package com.feliciano.store.dto;

import com.feliciano.store.resources.domain.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO implements Serializable {
	private Integer id;
	private String name;

	public CityDTO(City obj) {
		id = obj.getId();
		name = obj.getName();
	}

}
