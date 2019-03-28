package com.ejsistemas.semsa.model;

public enum Status_Movimento_Requisicao {

	ESPERA("EM ESPERA"),
	CANCELADO("CANCELADO"),
	MARCADO("MARCADO");
	
	private String descricao;
	
	Status_Movimento_Requisicao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
