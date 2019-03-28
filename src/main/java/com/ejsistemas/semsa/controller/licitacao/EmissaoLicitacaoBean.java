package com.ejsistemas.semsa.controller.licitacao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.event.LicitacaoAlteradoEvent;
import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.service.EmissaoLicitacaoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@RequestScoped
public class EmissaoLicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoLicitacaoService emissaoLicitacaoService;
	
	@Inject
	@LicitacaoEdicao
	private Licitacao licitacao;
	
	@Inject
	private Event<LicitacaoAlteradoEvent> licitacaoAlteradoEvent;
	
	public void emitirLicitacao(){
		this.licitacao.removerItemVazio();
		
		try{
			this.licitacao = this.emissaoLicitacaoService.emitir(this.licitacao);
			this.licitacaoAlteradoEvent.fire(new LicitacaoAlteradoEvent(this.licitacao));
			
			
			FacesUtil.addInfoMessage("Contrato emitido com sucesso!");
		}	finally{
			this.licitacao.adicionarItemVazio();
		}
	}

}
