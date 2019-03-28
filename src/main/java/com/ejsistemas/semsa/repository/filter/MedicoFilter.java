package com.ejsistemas.semsa.repository.filter;

import java.io.Serializable;

public class MedicoFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String cns;
	private String crm;
	private String nr_crm;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCns() {
		return cns;
	}
	public void setCns(String cns) {
		this.cns = cns;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getNr_crm() {
		return nr_crm;
	}
	public void setNr_crm(String nr_crm) {
		this.nr_crm = nr_crm;
	}

	
	
}
