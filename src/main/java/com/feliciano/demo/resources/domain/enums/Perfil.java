package com.feliciano.demo.resources.domain.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

    private final int cod;
    private final String descricao;

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil p : Perfil.values()) {
            if (cod.equals(p.getCod())) {
                return p;
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
