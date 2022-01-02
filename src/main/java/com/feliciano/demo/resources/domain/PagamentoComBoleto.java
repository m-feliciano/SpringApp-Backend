package com.feliciano.demo.resources.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.feliciano.demo.resources.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yy HH:mm")
    @Getter @Setter private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yy HH:mm")
    @Getter @Setter private Date dataPagamento;

    public PagamentoComBoleto() {
        super();
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
                              Date dataPagamento) {
        super(id, estado, pedido);
        this.setDataVencimento(dataVencimento);
        this.setDataPagamento(dataPagamento);
    }
}
