package com.ejsistemas.semsa.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.model.Status_Pericia;

public class PericiaFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;
	private Paciente paciente;
	private Date dataAntes;
	private Date dataDepois;
	private int primeiroRegistro;
	private int quantidadeRegistros;
	private Status_Pericia status;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}
	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}
	public int getQuantidadeRegistros() {
		return quantidadeRegistros;
	}
	public void setQuantidadeRegistros(int quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Date getDataAntes() {
		return dataAntes;
	}
	public void setDataAntes(Date dataAntes) {
		this.dataAntes = dataAntes;
	}
	public Date getDataDepois() {
		return dataDepois;
	}
	public void setDataDepois(Date dataDepois) {
		this.dataDepois = dataDepois;
	}
	public Status_Pericia getStatus() {
		return status;
	}
	public void setStatus(Status_Pericia status) {
		this.status = status;
	}
	
	
	
}
