package com.ejsistemas.semsa.controller.requisicao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.event.RequisicaoAlteradoEvent;
import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.service.CancelamentoRequisicaoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;


@Named
@RequestScoped
public class CancelamentoRequisicaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelamentoRequisicaoService cancelamentoRequisicaoService;
	
	@Inject
	private Event<RequisicaoAlteradoEvent> requisicaoAlteradoEvent;
	
	@Inject
	@RequisicaoEdicao
	private Requisicao requisicao;
	
	public void cancelarRequisicao(){
		this.requisicao = this.cancelamentoRequisicaoService.cancelar(this.requisicao);
		this.requisicaoAlteradoEvent.fire(new RequisicaoAlteradoEvent(this.requisicao));
		
		FacesUtil.addInfoMessage("Requisição Cancelada com sucesso!");
	}
	

}
