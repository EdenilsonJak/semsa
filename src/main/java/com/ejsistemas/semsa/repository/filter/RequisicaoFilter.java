package com.ejsistemas.semsa.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.ejsistemas.semsa.model.StatusPedido;

public class RequisicaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long cod;
	private Date dataInicio;
	private Date dataFim;
	private StatusPedido[] statuses;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public StatusPedido[] getStatuses() {
		return statuses;
	}

	public void setStatuses(StatusPedido[] statuses) {
		this.statuses = statuses;
	}
	
	
	
	

}
