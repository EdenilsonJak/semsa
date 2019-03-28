package com.ejsistemas.semsa.model;

public enum TipoEstabelecimento {

	CENTROESPECIALIZADO("CENTRO ESPECIALIZADO"),
	FORNECEDOR("FORNECEDOR"),
	HOSPITAL("HOSPITAL");
	
	private String descricao;

	TipoEstabelecimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
