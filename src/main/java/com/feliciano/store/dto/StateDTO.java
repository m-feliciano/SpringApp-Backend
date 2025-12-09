package com.feliciano.store.dto;

import com.feliciano.store.resources.domain.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StateDTO implements Serializable {
	private Integer id;
	private String name;

	public StateDTO(State obj) {
		id = obj.getId();
		name = obj.getName();
	}
}
