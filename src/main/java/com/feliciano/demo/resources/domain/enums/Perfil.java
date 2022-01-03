package com.feliciano.demo.resources.domain.enums;

import lombok.Getter;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENTE");

	@Getter
	private final int cod;
	@Getter
	private final String description;

	Perfil(int cod, String descricao) {
		this.cod = cod;
		this.description = descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil p : Perfil.values()) {
			if (cod.equals(p.getCod())) {
				return p;
			}
		}
		throw new IllegalAccessError("Invalid Id" + cod);
	}
}
