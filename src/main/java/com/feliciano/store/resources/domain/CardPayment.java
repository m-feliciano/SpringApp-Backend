package com.feliciano.store.resources.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.store.resources.domain.enums.PaymentStatus;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
    private Integer installments;

	public CardPayment(Integer id, PaymentStatus status, Order order, Integer installments) {
		super(id, status, order);
		this.installments = installments;
	}
}
