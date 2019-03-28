package com.ejsistemas.semsa.model;

public enum Status_Pericia {

	RECEBIDO("RECEBIDO"),
	PENDENTE("PENDENTE"),
	ENTREGUE("ENTREGUE");
	
	private String descricao;
	
	Status_Pericia(String descricao){
		this.descricao = descricao;
	};
	
	public String getDescricao() {
		return descricao;
	}
	
}
