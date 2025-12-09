package com.feliciano.store.resources.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.feliciano.store.resources.domain.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@Table(name = "tb_payment")
public abstract class Payment implements Serializable {
	@Id
	private Integer id;
	private Integer status;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	private Order order;

	public Payment(Integer id, PaymentStatus status, Order order) {
		super();
		this.setId(id);
		this.setStatus(status);
		this.setOrder(order);
	}

	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}

	public void setStatus(PaymentStatus status) {
		this.status = (status == null) ? null : status.getCod();
	}
}
