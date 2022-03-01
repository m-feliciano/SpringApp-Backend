package com.feliciano.demo.resources.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.demo.resources.domain.enums.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {

	private static final long serialVersionUID = 1L;
	private Integer installmentsNumber;

	public CardPayment(Integer id, PaymentStatus status, Order order, Integer numeroDeParcelas) {
		super(id, status, order);
		this.setInstallmentsNumber(numeroDeParcelas);
	}
}
