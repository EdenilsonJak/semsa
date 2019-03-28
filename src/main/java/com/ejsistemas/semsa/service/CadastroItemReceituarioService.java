package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Status_Movimento_Requisicao;
import com.ejsistemas.semsa.repository.ItemReceituarioRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

public class CadastroItemReceituarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemReceituarioRepository itemReceituarioRepository;

	@Transactional
	public void salvarMarcacaoItemReceituario(ItemReceituario itemReceituario) {
		ItemReceituario item = itemReceituarioRepository.porId(itemReceituario.getId());

		if (item.getMovimento_status().equals(Status_Movimento_Requisicao.ESPERA)) {
			itemReceituario.setMovimento_status(Status_Movimento_Requisicao.MARCADO);
			itemReceituario.setDataMarcacao(new Date());
			itemReceituarioRepository.guardar(itemReceituario);
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("sucesso", true);
			FacesUtil.addInfoMessage("Solicitação de " + itemReceituario.getSolicitacao().getDescricao());
			FacesUtil.addInfoMessage(
					"marcado com sucesso para o Paciente: " + itemReceituario.getReceituario().getPaciente().getNome());
			requestContext.update("msg");
		} else {
			FacesUtil.addAlerMessage("Esta requisição não pode ser marcada com o Status " + item.getMovimento_status());

		}
	}
	
	@Transactional
	public void salvarCancelamentoItemReceituario(ItemReceituario itemReceituario){
		ItemReceituario item = itemReceituarioRepository.porId(itemReceituario.getId());
		
		if(item.getMovimento_status().equals(Status_Movimento_Requisicao.ESPERA)
				|| item.getMovimento_status().equals(Status_Movimento_Requisicao.MARCADO)){

			itemReceituario.setMovimento_status(Status_Movimento_Requisicao.CANCELADO);
			itemReceituario.setDataCancelado(new Date());
			itemReceituarioRepository.guardar(itemReceituario);
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("sucesso", true);
			FacesUtil.addInfoMessage("Solicitação de " + itemReceituario.getSolicitacao().getDescricao());
			FacesUtil.addInfoMessage(
					"cancelado com sucesso para o Paciente: " + itemReceituario.getReceituario().getPaciente().getNome());
			requestContext.update("msg");		
			
		}
		else{
			FacesUtil.addAlerMessage("Requisição já encontra-se, "+item.getMovimento_status().getDescricao());
		}
	}

}
