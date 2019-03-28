package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Solicitacao;
import com.ejsistemas.semsa.repository.ItemReceituarioRepository;
import com.ejsistemas.semsa.repository.SolicitaoRepository;
import com.ejsistemas.semsa.repository.filter.RequisicaoRecepcaoFilter;

@Named
@ViewScoped
public class PesquisaItensReceituario implements Serializable {

	/**
	 * Desenvolvido por Edenilson Mendonça
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemReceituarioRepository itemReceituarioRepository;
	@Inject
	private SolicitaoRepository solicitacaoRepository;
	
	private RequisicaoRecepcaoFilter filtro = new RequisicaoRecepcaoFilter();
	
	private LazyDataModel<ItemReceituario> lazyModel;
	private LazyDataModel<ItemReceituario> lazyModelVazio;
	private List<Solicitacao> solicitacaoRaizes = new ArrayList<>();
	private Solicitacao solicitaoRaiz;
	private	List<Solicitacao> solicitacoes;
	
	@PostConstruct
	public void init(){
		solicitaoRaiz = new Solicitacao();
		solicitacoes = new ArrayList<>();
		solicitacaoRaizes = solicitacaoRepository.raizes();
		
	}

	public PesquisaItensReceituario() {
		
//		this.solicitacaoRaizes = solicitacaoRepository.raizes();
		
		lazyModel = new LazyDataModel<ItemReceituario>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<ItemReceituario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				if(filtro.getSolicitacao() == null && filtro.getStatus() == null){
				 return	(List<ItemReceituario>) (lazyModelVazio = null);
				}
				
				else{
					
					filtro.setPrimeiroRegistro(first);
					filtro.setQuantidadeRegistros(pageSize);
					
					setRowCount(itemReceituarioRepository.quantidadeFiltrados(filtro));
					
					return  itemReceituarioRepository.filtrados(filtro);
				}
			}
			
		};
		
	}

	public String limparDialog(CloseEvent event){
		return "ConsultaListaPosicoesFilter.xhtml?faces-redirect=true";
	}
	
	
	public LazyDataModel<ItemReceituario> getLazyModel() {
		return lazyModel;
	}
	
	public LazyDataModel<ItemReceituario> getLazyModelVazio() {
		return lazyModelVazio;
	}
	
	public RequisicaoRecepcaoFilter getFiltro() {
		return filtro;
	}
	
	public List<Solicitacao> getSolicitacaoRaizes() {
		return solicitacaoRaizes;
	}
	
	public Solicitacao getSolicitaoRaiz(){
		return solicitaoRaiz;
	}
	
	public void setSolicitaoRaiz(Solicitacao solicitaoRaiz) {
		this.solicitaoRaiz = solicitaoRaiz;
	}
	
	public void carregarSolicitacoesFilhas(ValueChangeEvent e) {
		solicitaoRaiz = (Solicitacao) e.getNewValue();
		this.solicitacoes = solicitacaoRepository.solicitacaoDe(solicitaoRaiz);
	}
	
	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}
	
	public void atualizarSolicitacoes(){
		if(solicitaoRaiz != null && solicitaoRaiz.getDescricao() != null){
			//solicitaoRaiz.getSubsolicitacao().listIterator();
			this.solicitacoes = solicitacaoRepository.solicitacaoDe(solicitaoRaiz);
		}
	}
}

