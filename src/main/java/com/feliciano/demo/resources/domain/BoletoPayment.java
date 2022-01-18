package com.feliciano.demo.resources.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.demo.resources.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("boletoPayment")
public class BoletoPayment extends Payment {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Getter
	@Setter
	private Date dueDate;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Getter
	@Setter
	private Date paymentDate;

	public BoletoPayment() {
		super();
	}

	public BoletoPayment(Integer id, PaymentStatus state, Order order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.setDueDate(dueDate);
		this.setPaymentDate(paymentDate);
	}
}
