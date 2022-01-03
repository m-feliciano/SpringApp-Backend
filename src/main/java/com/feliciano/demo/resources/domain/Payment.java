package com.feliciano.demo.resources.domain;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.feliciano.demo.resources.domain.enums.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@Table(name = "tb_payment")
public abstract class Payment implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	private Integer id;
	private Integer status;

	@JsonIgnore // deny serialization
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	@Getter
	@Setter
	private Order order;

	public Payment(Integer id, PaymentStatus status, Order order) {
		super();
		this.setId(id);
		this.setStatus(status);
		this.setOrder(order);
	}

	public PaymentStatus getEstado() {
		return PaymentStatus.toEnum(status);
	}

	public void setStatus(PaymentStatus status) {
		this.status = (status == null) ? null : status.getCod();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
