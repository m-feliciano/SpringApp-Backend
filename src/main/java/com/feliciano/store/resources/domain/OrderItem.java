package com.feliciano.store.resources.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

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
		return new StringBuilder()
				.append(getProduct().getName())
				.append(", Qtd: ")
				.append(getQuantity())
				.append(", Unit price: ")
				.append(nf.format(getPrice()))
				.append(", Subtotal: ")
				.append(nf.format(getSubtotal())).append("\n").toString();
	}
}
