package com.feliciano.demo.resources.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class ItemPedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore // deny serialization from here
    @EmbeddedId // built-in helper id
    private ItemPedidoPK id = new ItemPedidoPK();

    @Getter
    @Setter
    private Double desconto;
    @Getter
    @Setter
    private Integer quantidade;
    @Getter
    @Setter
    private Double preco;

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubtotal() {
        return (preco - desconto) * quantidade;
    }

    /**
     * @return the id
     */
    @JsonIgnore // deny serialization on execution
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
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
        ItemPedido other = (ItemPedido) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return new StringBuilder()
                .append(getProduto().getNome())
                .append(", Qtd: ")
                .append(getQuantidade())
                .append(", Preco unitario: ")
                .append(nf.format(getPreco()))
                .append(", Subtotal: ")
                .append(nf.format(getSubtotal()))
                .append("\n")
                .toString();
    }
}
