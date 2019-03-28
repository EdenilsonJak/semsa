package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.event.InternaAlteradoEvent;
import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.qualificador.edicao.InternaEdicao;
import com.ejsistemas.semsa.service.AltaInternacaoService;
import com.ejsistemas.semsa.service.CadastroLeitoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@RequestScoped
public class AltaInternacaoBean implements Serializable {

	/**
	 * Classe responsável por alta de paciente 
	 * criada e analisada por Edenilson Mendonça dos Santos 
	 * @2016 todos os direitos reservados
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	AltaInternacaoService altaInternacaoService;
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
	
	
	/*Método responsável por realizar 
	* alta de paciente utilizando
	* classe altaInternacaoService!*/
	
	public String altaInternacaoPaciente(){
		if(this.internacao.isOcupado()){
			this.internacao.setDataAlta(LocalDateTime.now());
			this.internacao.setUsuarioAlta(seguranca.getUsuario());
			this.internacao = this.altaInternacaoService.alta(this.internacao);
			this.setLeito(this.internacao.getLeito());
			this.getLeito().setStatus("AUSENTE");
			cadastroLeitoService.salvar(leito);
			this.internacaoAlteradoEvent.fire(new InternaAlteradoEvent(internacao));

			RequestContext request = RequestContext.getCurrentInstance();
			request.addCallbackParam("sucesso", true);

			FacesUtil.addInfoMessage("Alta paciente: "+this.internacao.getPaciente().getNome());
			FacesUtil.addInfoMessage("Realizado com sucesso!");
			this.internacao = new Internacao();
			
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
}
