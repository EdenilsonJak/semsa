package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Produto;
import com.ejsistemas.semsa.repository.ProdutoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoRepository produtos;
	
	@Transactional
	public Produto salvar(Produto produto){
		//Produto produtoExistente = produtos.porNome(produto.getNome());
		//if(produtoExistente != null && !produtoExistente.equals(produto)){
		//	throw new NegocioException("Já existe um produto com SKU informado!");
		//}
		return produtos.guardar(produto);
		
	}

}
