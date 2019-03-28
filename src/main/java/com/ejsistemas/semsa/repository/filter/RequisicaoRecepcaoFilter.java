package com.ejsistemas.semsa.repository.filter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.model.Solicitacao;
import com.ejsistemas.semsa.model.Status_Movimento_Requisicao;
import com.ejsistemas.semsa.model.Status_Prioridade_Requisicao;
import com.ejsistemas.semsa.model.UnidadeSaude;

public class RequisicaoRecepcaoFilter implements Serializable{

	/**
	 * Filtro para pesquisa de requisições de recepção
	 */
	private static final long serialVersionUID = 1L;

	private Date dataInicioRequisicao;
	private Date dataFimRequisicao;
	private Date dataInicioRequisicaoMarcacao;
	private Date dataFimRequisicaoMarcacao;
	private Solicitacao solicitacao;
	private Paciente paciente;
	private UnidadeSaude unidade;
	private Status_Prioridade_Requisicao status = null;
	private Status_Movimento_Requisicao status_movimento = null;
	private int primeiroRegistro;
	private int quantidadeRegistros;
	
	public Date getDataInicioRequisicao() {
		return dataInicioRequisicao;
	}
	public void setDataInicioRequisicao(Date dataInicioRequisicao) {
		this.dataInicioRequisicao = dataInicioRequisicao;
	}
	public Date getDataFimRequisicao() {
		return dataFimRequisicao;
	}
	public void setDataFimRequisicao(Date dataFimRequisicao) {
		this.dataFimRequisicao = dataFimRequisicao;
	}	
	public Date getDataInicioRequisicaoMarcacao() {
		return dataInicioRequisicaoMarcacao;
	}
	public void setDataInicioRequisicaoMarcacao(Date dataInicioRequisicaoMarcacao) {
		this.dataInicioRequisicaoMarcacao = dataInicioRequisicaoMarcacao;
	}
	public Date getDataFimRequisicaoMarcacao() {
		return dataFimRequisicaoMarcacao;
	}
	public void setDataFimRequisicaoMarcacao(Date dataFimRequisicaoMarcacao) {
		this.dataFimRequisicaoMarcacao = dataFimRequisicaoMarcacao;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public UnidadeSaude getUnidade() {
		return unidade;
	}
	public void setUnidade(UnidadeSaude unidade) {
		this.unidade = unidade;
	}	
	@Enumerated(EnumType.STRING)
	public Status_Prioridade_Requisicao getStatus() {
		return status;
	}
	
	public void setStatus(Status_Prioridade_Requisicao status) {
		this.status = status;
	}
	
	public Status_Movimento_Requisicao getStatus_movimento() {
		return status_movimento;
	}
	
	public void setStatus_movimento(Status_Movimento_Requisicao status_movimento) {
		this.status_movimento = status_movimento;
	}
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}
	
	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
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
}
