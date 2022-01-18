package com.feliciano.demo.resources.domain;

import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@JsonIgnore // deny serialization from here
	@EmbeddedId // built-in helper id
	private OrderItemPK id = new OrderItemPK();

	@Getter
	@Setter
	private Double discount;
	@Getter
	@Setter
	private Integer quantity;
	@Getter
	@Setter
	private Double price;

	public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	public double getSubtotal() {
		return (price - discount) * quantity;
	}

	/**
	 * @return the id
	 */
	@JsonIgnore // deny serialization on execution
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return new StringBuilder().append(getProduct().getName()).append(", Qtd: ").append(getQuantity())
				.append(", Unit price: ").append(nf.format(getPrice())).append(", Subtotal: ")
				.append(nf.format(getSubtotal())).append("\n").toString();
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
