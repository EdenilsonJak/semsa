package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.SSLEngineResult.Status;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Receituario;
import com.ejsistemas.semsa.model.Status_Prioridade_Requisicao;
import com.ejsistemas.semsa.service.CadastroReceituarioService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@ViewScoped
public class RemarcarItemReceituarioRecepcao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemReceituario itemReceituario;
	@Inject
	private Receituario receituario;
	@Inject
	private ItemReceituario itemReceituarioAlter;
	@Inject
	private CadastroReceituarioService cadastroReceituarioService;
	@Inject
	private Seguranca usuario;

	public ItemReceituario getItemReceituarioAlter() {
		return itemReceituarioAlter;
	}

	public void setItemReceituarioAlter(ItemReceituario itemReceituarioAlter) {
		this.itemReceituarioAlter = itemReceituarioAlter;
	}

	public ItemReceituario getItemReceituario() {
		return itemReceituario;
	}

	public void setItemReceituario(ItemReceituario itemReceituario) {
		this.itemReceituario = itemReceituario;
	}

	public Receituario getReceituario() {
		return receituario;
	}

	public void setReceituario(Receituario receituario) {
		this.receituario = receituario;
	}

	public void remarcarItemReceituario() {
		if (itemReceituario.isExistente() && itemReceituario.isCancelado() || itemReceituario.isMarcado()) {
			receituario.setPaciente(itemReceituario.getReceituario().getPaciente());
			receituario.setData(new Date());
			receituario.setUsuarioEntrada(usuario.getUsuario());

			itemReceituarioAlter.setDataRequisicao(itemReceituario.getDataRequisicao());
			itemReceituarioAlter.setMedico(itemReceituario.getMedico());
			itemReceituarioAlter.setSolicitacao(itemReceituario.getSolicitacao());
			itemReceituarioAlter.setUnidadeSaude(itemReceituario.getUnidadeSaude());

			itemReceituarioAlter.setReceituario(receituario);
			
			if (itemReceituario.getPrioridade_status().equals(Status_Prioridade_Requisicao.IDOSO)) {
				itemReceituarioAlter.setPrioridade_status(Status_Prioridade_Requisicao.IDOSO);
			}
			if (itemReceituario.getPrioridade_status().equals(Status_Prioridade_Requisicao.DEFICIENTEFISICO)) {
				itemReceituarioAlter.setPrioridade_status(Status_Prioridade_Requisicao.DEFICIENTEFISICO);
			}
			if (itemReceituario.getPrioridade_status().equals(Status_Prioridade_Requisicao.ACAOJUDICIAL)) {
				itemReceituarioAlter.setPrioridade_status(Status_Prioridade_Requisicao.ACAOJUDICIAL);
			}
			if (itemReceituario.getPrioridade_status().equals(Status_Prioridade_Requisicao.RISCODEVIDA)) {
				itemReceituarioAlter.setPrioridade_status(Status_Prioridade_Requisicao.RISCODEVIDA);
			}

			receituario.getItens().add(itemReceituarioAlter);
			cadastroReceituarioService.salvar(receituario);

			itemReceituario = new ItemReceituario();
			receituario = new Receituario();

		} else {
			FacesUtil.addAlerMessage("Não é possível remarcar Solicitação com Status em "
					+ itemReceituario.getMovimento_status().getDescricao());
		}
	}

}
