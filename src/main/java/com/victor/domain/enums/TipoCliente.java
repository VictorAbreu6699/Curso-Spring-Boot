package com.victor.domain.enums;

import java.util.Iterator;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod)
	{
		TipoCliente resultado = null;
		for (TipoCliente var: TipoCliente.values()) {
			if (cod.equals(var.getCod())) {
				resultado = var;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
