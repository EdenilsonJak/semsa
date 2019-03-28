package com.ejsistemas.semsa.model;

public enum StatusTipoDocumento {

	RG("RG"),
	CPF("CPF"),
	CNPJ("CNPJ");
	
	private String descricao;

	StatusTipoDocumento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
