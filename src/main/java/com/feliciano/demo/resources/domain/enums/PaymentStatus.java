package com.feliciano.demo.resources.domain.enums;

import lombok.Getter;

public enum PaymentStatus {

	PENDING(1, "Pending"), PAID(2, "Paid"), CANCELED(3, "Canceled");

	@Getter
	private final int cod;
	@Getter
	private final String descricao;

	PaymentStatus(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static PaymentStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (PaymentStatus ep : PaymentStatus.values()) {
			if (cod.equals(ep.getCod())) {
				return ep;
			}
		}
		throw new IllegalAccessError("Invalid Id" + cod);
	}
}
