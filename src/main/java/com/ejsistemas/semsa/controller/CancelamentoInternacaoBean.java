package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.event.InternaAlteradoEvent;
import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.qualificador.edicao.InternaEdicao;
import com.ejsistemas.semsa.service.CadastroLeitoService;
import com.ejsistemas.semsa.service.CancelamentoInternacaoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@RequestScoped
public class CancelamentoInternacaoBean implements Serializable {

	/**
	 * Desenvolvido por Edenilson Mendonça proprietário ejsistemas Classe java
	 * responsável por cancelar Interção
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoInternacaoService cancelamentoInternacaoService;
	@Inject
	CadastroLeitoService cadastroLeitoService;

	@Inject
	private Event<InternaAlteradoEvent> internacaoAlteradoEvent;
	
	@Inject
	private Seguranca seguranca;

	@Inject
	@InternaEdicao
	private Internacao internacao;
	private Leito leito;

	/*
	 * Método responsável por cancelar interção e utilizado classe
	 * cancelamentoInternacaoService que faz transação e commit no banco
	 */
	public String cancelarInternacao() {
		if (this.internacao.isExistente()) {
			this.internacao.setDataCancelamento(LocalDateTime.now());
			this.internacao.setUsuarioCancelador(seguranca.getUsuario());
			this.internacao = this.cancelamentoInternacaoService.cancelar(this.internacao);
			this.setLeito(this.internacao.getLeito());
			this.getLeito().setStatus("AUSENTE");
			cadastroLeitoService.salvar(leito);
			
			
			/*this.internacaoAlteradoEvent.fire(new InternaAlteradoEvent(internacao));

			RequestContext request = RequestContext.getCurrentInstance();
			request.addCallbackParam("sucesso", true);*/

			FacesUtil.addInfoMessage("Internação, chave: "+ internacao.getId() +" cancelado com sucesso!");
			
			
			FacesContext.getCurrentInstance()
			.getExternalContext()
			.getFlash().setKeepMessages(true);
			
			return "Internacao.xhtml?faces-redirect=true";

		} else {
			FacesUtil.addAlerMessage("Obs e Motivo são campos obrigatórios!");
		}
		return null;

	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public Internacao getInternacao() {
		return internacao;
	}
	
}
