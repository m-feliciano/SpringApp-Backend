package com.feliciano.demo.resources.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.demo.resources.domain.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@JsonTypeName("BoletoPayment")
public class BoletoPayment extends Payment {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Getter
	@Setter
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Getter
	@Setter
	private Date dataPagamento;

	public BoletoPayment() {
		super();
	}

	public BoletoPayment(Integer id, PaymentStatus state, Order order, Date dataVencimento, Date dataPagamento) {
		super(id, state, order);
		this.setDataVencimento(dataVencimento);
		this.setDataPagamento(dataPagamento);
	}
}
