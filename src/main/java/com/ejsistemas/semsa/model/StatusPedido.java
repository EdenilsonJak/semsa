package com.ejsistemas.semsa.model;

public enum StatusPedido {

	ORCAMENTO("ORÇAMENTO"),
	EMITIDO("EMITIDO"),
	CANCELADO("CANCELADO"),
	INICIAL("INICIAL");
	
	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
