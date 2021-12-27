package com.feliciano.demo.resources.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

    private final int cod;
    private final String descricao;

    EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (EstadoPagamento ep : EstadoPagamento.values()) {
            if (cod.equals(ep.getCod())) {
                return ep;
            }
        }
        throw new IllegalAccessError("Id invalido" + cod);
    }

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
}
