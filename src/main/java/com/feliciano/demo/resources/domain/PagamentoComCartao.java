package com.feliciano.demo.resources.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.demo.resources.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serial;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    @Serial
    private static final long serialVersionUID = 1L;
   private Integer numeroDeParcelas;

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.setNumeroDeParcelas(numeroDeParcelas);
    }
}
