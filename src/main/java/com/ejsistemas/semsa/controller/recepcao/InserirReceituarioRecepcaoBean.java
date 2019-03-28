package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.event.ReceituarioAlteradoEvent;
import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Receituario;
import com.ejsistemas.semsa.model.Status_Prioridade_Requisicao;

@Named
@ViewScoped
public class InserirReceituarioRecepcaoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	@ReceituarioEdicao
	private Receituario receituario;
	
	@Inject
	private ItemReceituario itensRequisicoes;
	
	@Inject
	private Event<ReceituarioAlteradoEvent> receituarioAlteradoEvent;
		
	public void inicializar(){
		
	}

	public void lerStatusPrioridade(ValueChangeEvent event){
		itensRequisicoes.setPrioridade_status((Status_Prioridade_Requisicao) event.getNewValue());
	}
	
	public void adicionarItemRequisicao(){
		itensRequisicoes.setReceituario(receituario);
		receituario.getItens().add(itensRequisicoes);
		this.receituarioAlteradoEvent.fire(new ReceituarioAlteradoEvent(this.receituario));
		this.itensRequisicoes = new ItemReceituario();
	}
	
	public ItemReceituario getItensRequisicoes() {
		return itensRequisicoes;
	}
	
	public void setItensRequisicoes(ItemReceituario itensRequisicoes) {
		this.itensRequisicoes = itensRequisicoes;
	}
}