package com.ejsistemas.semsa.repository.filter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.model.Paciente;

public class InternacaoFilter  implements Serializable{

	private static final long serialVersionUID = 1L;

	private String status;
	private Paciente paciente;
	private Fornecedor hospital;
	private Clinica clinica;
	private Leito leito;
	private Internacao internacao;
	private Date inicio;
	private Date fim;
	
	public Fornecedor getHospital() {
		return hospital;
	}

	public void setHospital(Fornecedor hospital) {
		this.hospital = hospital;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Internacao getInternacao() {
		return internacao;
	}
	
	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	
		
}
