package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.service.CancelamentoItemRequisicaoService;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@ViewScoped
public class CancelamentoRequisicaoRecepcao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemReceituario itemReceituario;
	@Inject
	private CancelamentoItemRequisicaoService cancelamentoItemRequisicaoService;
	@Inject
	private Seguranca usuario;

	public void setItemReceituario(ItemReceituario itemReceituario) {
		this.itemReceituario = itemReceituario;
	}

	public ItemReceituario getItemReceituario() {
		return itemReceituario;
	}

	public void salvarCancelamento() {
		itemReceituario.setUsuarioCancela(usuario.getUsuario());
		cancelamentoItemRequisicaoService.salvarCancelamentoItemReceituario(itemReceituario);

	}

}
