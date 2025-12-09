package com.feliciano.store.resources.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feliciano.store.resources.domain.enums.ClientType;
import com.feliciano.store.resources.domain.enums.Perfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "tb_client")
public class Client implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private final Set<Integer> perfis = new HashSet<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer type;

	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
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

	public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType type, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.password = senha;
		this.type = (type == null) ? null : type.getCod();
		addPerfil(Perfil.CLIENT);
	}

	/**
	 * @return the typeCliente
	 * @throws IllegalAccessException
	 */
	public ClientType getType() throws IllegalAccessException {
		return ClientType.toEnum(type);
	}

	/**
	 * @param type the typeCliente to set
	 */
	public void setType(ClientType type) {
		this.type = type.getCod();
	}

	public Set<Perfil> getPerfils() {
		return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}
