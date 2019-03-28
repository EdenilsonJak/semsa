package com.ejsistemas.semsa.controller.requisicao;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.event.RequisicaoAlteradoEvent;
import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.service.EmissaoRequisicaoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EmissaoRequisicaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoRequisicaoService emissaoRequisicaoService;
	
	@Inject
	@RequisicaoEdicao
	private Requisicao requisicao;
	
	@Inject
	private Event<RequisicaoAlteradoEvent> requisicaoAlteradoEvent;
	
	public void emitirRequisicao(){
		
		this.requisicao.removeItemVazio();
		
		try{
			this.requisicao = this.emissaoRequisicaoService.emitir(this.requisicao);
			this.requisicaoAlteradoEvent.fire(new RequisicaoAlteradoEvent(this.requisicao));
			
			FacesUtil.addInfoMessage("Requisição emitido com sucesso.");
		}finally{
			this.requisicao.adicionarItemVazio();
		}
		
		
	}

}
