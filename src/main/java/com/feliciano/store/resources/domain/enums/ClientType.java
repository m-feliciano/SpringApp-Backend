package com.feliciano.store.resources.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ClientType {

	PERSON(1, "Person"), ENTITY(2, "Entity");

	@Getter
	private final int cod;
	@Getter
	private final String descricao;

	public static ClientType toEnum(Integer cod) throws IllegalAccessException {
		if (cod == null) {
			return null;
		}
		for (ClientType tc : ClientType.values()) {
			if (cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalAccessException("Invalid Id" + cod);
	}
}
