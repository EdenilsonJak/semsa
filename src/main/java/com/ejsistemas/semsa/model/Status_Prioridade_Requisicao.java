package com.ejsistemas.semsa.model;

public enum Status_Prioridade_Requisicao {

	NORMAL("NORMAL"),
	RISCODEVIDA("RISCO DE VIDA"),
	ACAOJUDICIAL("AÇÃO JUDICIAL"),
	DEFICIENTEFISICO("DEFICIENTE FÍSICO"),
	IDOSO("POR IDADE");
	
	private String descricao;
	
	Status_Prioridade_Requisicao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
