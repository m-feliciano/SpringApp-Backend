package com.feliciano.store.resources.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum PaymentStatus {
    PENDING(1, "Pending"),
    PAID(2, "Paid"),
    CANCELED(3, "Canceled");

	@Getter
	private final int cod;
	@Getter
	private final String description;

    @JsonCreator
    PaymentStatus(@JsonProperty("cod") int cod,
                  @JsonProperty("description") String description) {
		this.cod = cod;
		this.description = description;
	}

	public static PaymentStatus toEnum(Integer cod) {
        if (cod == null) return null;

		for (PaymentStatus ep : PaymentStatus.values()) {
			if (cod.equals(ep.getCod())) {
				return ep;
			}
		}
		throw new IllegalAccessError("Invalid Id" + cod);
	}
}
