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
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.service.CadastroInternacaoService;
import com.ejsistemas.semsa.service.CadastroLeitoService;
import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.service.TransferenciaPacienteService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@RequestScoped
public class TransferenciaPacienteBean implements Serializable {

	/**
	 * Classe responsável pela transferência de paciente e checagem de leitos disponíveis
	 * Desenvolvido e mantido por Edenilson Mendonça dos Santos
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	LeitoRepository leitoRepository;
	
	@Inject
	TransferenciaPacienteService tranferenciaPacienteService;
	
	@Inject
	CadastroLeitoService cadastroLeitoService;
	
	@Inject
	CadastroInternacaoService cadastroiInternacaoService;
	
	@Inject
	private Event<InternaAlteradoEvent> internacaoAlteradoEvent;
	
	@Inject
	private Seguranca seguranca;
	
	@Inject
	@InternaEdicao
	private Internacao internacao;
	
	private Leito leito;
	
	private Internacao transferencia;
	
	public String transferenciaPaciente(){
		if(internacao.isOcupado()){
			if(chckeLeitoDisponivel(internacao)){
				transferencia = internacao.getInternacao();
				transferencia.setInternacao(null);
				transferencia.setDataAlta(LocalDateTime.now());
				transferencia.setUsuario(seguranca.getUsuario());
				//transferencia.setMotivo(internacao.getMotivo());
				//transferencia.setObs_alta(internacao.getObs_alta());
				transferencia.setStatus("TRANSFERIDO");
				
				transferencia = this.cadastroiInternacaoService.salvar(transferencia);
				
				this.setLeito(this.transferencia.getLeito());
				this.getLeito().setStatus("AUSENTE");
				cadastroLeitoService.salvar(leito);
				
				internacao.setId(null);
				internacao.setMotivo(null);
				internacao.setObs_alta(null);
				internacao.setDataAlta(null);
				internacao.setInternacao(transferencia);
				
				internacao.setDataInternacao(LocalDateTime.now());
				
				internacao = tranferenciaPacienteService.transferencia(internacao);
				
				this.setLeito(this.internacao.getLeito());
				this.getLeito().setStatus("OCUPADO");
				cadastroLeitoService.salvar(leito);	
				
				/*RequestContext request = RequestContext.getCurrentInstance();
				request.addCallbackParam("sucesso", true);*/

				FacesUtil.addInfoMessage("Alta paciente: "+this.internacao.getPaciente().getNome());
				FacesUtil.addInfoMessage("Realizado com sucesso!");
				this.internacao = new Internacao();
				
				FacesContext.getCurrentInstance()
				.getExternalContext()
				.getFlash().setKeepMessages(true);
				
				return "Internacao.xhtml?faces-redirect=true";
				
			}
			else{
				FacesUtil.addAlerMessage("Leito não disponível, por favor, verifique com a Administração!");
			}
			
		}else{
			throw new NegocioException("Paciente não pode ser transferido com status: "
					+ internacao.getStatus() + ".");
		}
		
		
		
		return null;
		
	}



	private boolean chckeLeitoDisponivel(Internacao internacao2) {
		boolean existeLeitoDisponivel = false;
		this.leito = this.leitoRepository.porId(internacao2.getLeito().getId());
		if(this.leito.getStatus().equals("AUSENTE")){
			existeLeitoDisponivel = true;
		}
		return existeLeitoDisponivel;
	}



	public Leito getLeito() {
		return leito;
	}
	
	public void setLeito(Leito leito) {
		this.leito = leito;
	}



	public Internacao getTransferencia() {
		return transferencia;
	}



	public void setTransferencia(Internacao transferencia) {
		this.transferencia = transferencia;
	}
	
	
	
}
