package com.feliciano.demo.resources.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public double getValorTotal() {
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
		builder.append(getPayment().getEstado().getDescricao());
		builder.append("\nDetails:\n");
		for (OrderItem ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("TotalValue: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
