package com.feliciano.demo.resources.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feliciano.demo.resources.domain.enums.Perfil;
import com.feliciano.demo.resources.domain.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private final Set<Integer> perfis = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOrCnpj;
    private Integer tipo;
    @JsonIgnore
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) // @cascate(ALL) delete in cascate(all) enderecos from
    // cliente
    private List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonBackReference // deny serialization by Pedido
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String cpfOrCnpj, TipoCliente tipo, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.senha = senha;
        this.tipo = (tipo == null) ? null : tipo.getCod();
        addPerfil(Perfil.CLIENTE);
    }

    /**
     * @return the tipoCliente
     * @throws IllegalAccessException
     */
    public TipoCliente getTipo() throws IllegalAccessException {
        return TipoCliente.toEnum(tipo);
    }

    /**
     * @param tipo the tipoCliente to set
     */
    public void setTipo(TipoCliente tipo) {
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
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return id != null && Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
