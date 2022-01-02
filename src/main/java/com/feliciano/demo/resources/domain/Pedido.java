package com.feliciano.demo.resources.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yy HH:mm")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoEntrega) {
        super();
        this.id = id;
        this.setInstante(instante);
        this.setCliente(cliente);
        this.setEnderecoEntrega(enderecoEntrega);
    }

    public double getValorTotal() {
        return itens.stream().map(ItemPedido::getSubtotal).reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append("\nInstante: ");
        builder.append(sdf.format(getInstante()));
        builder.append("\nCliente: ");
        builder.append(getCliente().getNome());
        builder.append("\nSituação do pagamento: ");
        builder.append(getPagamento().getEstado().getDescricao());
        builder.append("\nDetalhes:\n");
        for (ItemPedido ip : getItens()) {
            builder.append(ip.toString());
        }
        builder.append("ValorTotal: ");
        builder.append(nf.format(getValorTotal()));
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        Pedido other = (Pedido) obj;
        return Objects.equals(id, other.id);
    }


}
