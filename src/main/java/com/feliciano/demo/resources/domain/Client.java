package com.feliciano.demo.resources.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feliciano.demo.resources.domain.enums.ClientType;
import com.feliciano.demo.resources.domain.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_client")
public class Client implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private final Set<Integer> perfis = new HashSet<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer tipo;
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL) // @cascate(ALL) delete in cascate(all) enderecos from
	// cliente
	private List<Address> address = new ArrayList<>();
	@ElementCollection
	@CollectionTable(name = "PHONES")
	private Set<String> phones = new HashSet<>();

	@JsonBackReference // deny serialization by Pedido
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	public Client() {
		addPerfil(Perfil.CLIENT);
	}

	public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType tipo, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.password = senha;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		addPerfil(Perfil.CLIENT);
	}

	/**
	 * @return the tipoCliente
	 * @throws IllegalAccessException
	 */
	public ClientType getTipo() throws IllegalAccessException {
		return ClientType.toEnum(tipo);
	}

	/**
	 * @param tipo the tipoCliente to set
	 */
	public void setTipo(ClientType tipo) {
		this.tipo = tipo.getCod();
	}

	public Set<Perfil> getPerfils() {
		return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Client client = (Client) o;
		return id != null && Objects.equals(id, client.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
