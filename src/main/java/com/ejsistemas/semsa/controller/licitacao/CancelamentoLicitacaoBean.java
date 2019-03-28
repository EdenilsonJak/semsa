package com.ejsistemas.semsa.controller.licitacao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.event.LicitacaoAlteradoEvent;
import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.service.CancelamentoLicitacaoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoLicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelamentoLicitacaoService cancelamentoLicitacaoService;
	
	@Inject
	private Event<LicitacaoAlteradoEvent> licitacaoAlteradoEvent;
	
	@Inject
	@LicitacaoEdicao
	private Licitacao licitacao;
	
	public void cancelarLicitacao(){
		this.licitacao = this.cancelamentoLicitacaoService.cancelar(this.licitacao);
		this.licitacaoAlteradoEvent.fire(new LicitacaoAlteradoEvent(this.licitacao));
		
		FacesUtil.addInfoMessage("Contrato cancelado com sucesso!");
	}
	
	
}
