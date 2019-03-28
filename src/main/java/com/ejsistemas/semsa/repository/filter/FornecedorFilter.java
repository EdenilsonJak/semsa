package com.ejsistemas.semsa.repository.filter;

import java.io.Serializable;

public class FornecedorFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fornecedor;
	private String CNPJ;
	private String fantasia;
	
	
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	
	
	

}
