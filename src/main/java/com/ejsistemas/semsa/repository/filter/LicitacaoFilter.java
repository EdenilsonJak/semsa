package com.ejsistemas.semsa.repository.filter;

import java.util.Date;

import com.ejsistemas.semsa.model.StatusPedido;

public class LicitacaoFilter {

	private Long numeroDe;
	private Long numeroAte;
	private Date dataCadastroDe;
	private Date dataCadastroAte;
	private Integer numeroProcesso;
	private String tipoLicitacao;
	private String nomeModalidade;
	private StatusPedido[] statuses;

	public Long getNumeroDe() {
		return numeroDe;
	}

	public void setNumeroDe(Long numeroDe) {
		this.numeroDe = numeroDe;
	}

	public Long getNumeroAte() {
		return numeroAte;
	}

	public void setNumeroAte(Long numeroAte) {
		this.numeroAte = numeroAte;
	}

	public Date getDataCadastroDe() {
		return dataCadastroDe;
	}

	public void setDataCadastroDe(Date dataCadastroDe) {
		this.dataCadastroDe = dataCadastroDe;
	}

	public Date getDataCadastroAte() {
		return dataCadastroAte;
	}

	public void setDataCadastroAte(Date dataCadastroAte) {
		this.dataCadastroAte = dataCadastroAte;
	}

	public Integer getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(Integer numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}


	public String getTipoLicitacao() {
		return tipoLicitacao;
	}

	public void setTipoLicitacao(String tipoLicitacao) {
		this.tipoLicitacao = tipoLicitacao;
	}

	public String getNomeModalidade() {
		return nomeModalidade;
	}

	public void setNomeModalidade(String nomeModalidade) {
		this.nomeModalidade = nomeModalidade;
	}

	public StatusPedido[] getStatuses() {
		return statuses;
	}

	public void setStatuses(StatusPedido[] statuses) {
		this.statuses = statuses;
	}

}
