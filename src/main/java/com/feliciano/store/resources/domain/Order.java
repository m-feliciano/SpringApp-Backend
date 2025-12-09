package com.feliciano.store.resources.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_order")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Address enderecoEntrega;

	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> itens = new HashSet<>();

	public Order(Client cli, Address addr, Date date) {
		super();
		this.id = null;
		this.setInstant(date);
		this.setClient(cli);
		this.setEnderecoEntrega(addr);
	}

	public double getTotalValue() {
		return itens.stream().map(OrderItem::getSubtotal).reduce(0.0, Double::sum);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Order number: ");
		builder.append(getId());
		builder.append("\nInstant: ");
		builder.append(sdf.format(getInstant()));
		builder.append("\nClient: ");
		builder.append(getClient().getName());
		builder.append("\nPayment Status: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetails:\n");
		for (OrderItem ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("TotalValue: ");
		builder.append(nf.format(getTotalValue()));
		return builder.toString();
	}
}
