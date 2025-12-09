package com.feliciano.store.resources.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.store.resources.domain.enums.PaymentStatus;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonTypeName("boletoPayment")
public class BoletoPayment extends Payment {
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dueDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date paymentDate;

    public BoletoPayment(Integer id,
                         PaymentStatus status,
                         Order order,
                         Date dueDate,
                         Date paymentDate) {
        super(id, status, order);
		this.setDueDate(dueDate);
		this.setPaymentDate(paymentDate);
	}
}
