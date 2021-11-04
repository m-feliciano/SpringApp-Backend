package com.feliciano.demo.resources.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Fisica"), PESSOJURIDICA(2, "Pessoa Juridica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
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

	public static TipoCliente toEnum(Integer cod) throws IllegalAccessException {
		if (cod == null) {
			return null;
		}
		for (TipoCliente tc : TipoCliente.values()) {
			if (cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalAccessException("Id invalido" + cod);
	}
}
