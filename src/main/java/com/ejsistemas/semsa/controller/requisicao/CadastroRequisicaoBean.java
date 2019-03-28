package com.ejsistemas.semsa.controller.requisicao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.ejsistemas.semsa.event.RequisicaoAlteradoEvent;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.ItemLicitacao;
import com.ejsistemas.semsa.model.ItemRequisicao;
import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.ItemLicitacaoRepository;
import com.ejsistemas.semsa.repository.LicitacaoRepository;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.service.CadastroRequisicaoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class CadastroRequisicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private RequisicaoRepository requisicaoRepository;

	@Inject
	private CadastroRequisicaoService cadastroRequisicaoService;

	@Inject
	private LicitacaoRepository licitacaoRepository;
	
	@Inject 
	ItemLicitacaoRepository itemLicitacaoRepository;

	@Produces
	@RequisicaoEdicao
	private Requisicao requisicao;

	private Licitacao licitacao;
	
	private Fornecedor fornecedor;
	
	private ItemLicitacao itemLinhaEditavel;
	
	private List<Fornecedor> fornecedors;

	public CadastroRequisicaoBean() {
		this.limpar();
	}

	public void inicializar() {
			this.requisicao.adicionarItemVazio();
			this.recalcularRequisicao();
	}
	
	public void requisicaoAlterado(@Observes RequisicaoAlteradoEvent event){
		this.requisicao = event.getRequisicao();
	}

	public void salvar() {
		
		this.requisicao.removeItemVazio();
		
			try{
				
				this.requisicao = cadastroRequisicaoService.salvar(requisicao);
				
				FacesUtil.addInfoMessage("Requisição cadastrada com sucesso!");
				
			}finally{
				this.requisicao.adicionarItemVazio();			
			}
			
		}
		

	private void limpar() {
		this.requisicao = new Requisicao();
		this.licitacao = new Licitacao();
	}

	public boolean isEditando() {
		return this.getRequisicao().getId() != null;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public Licitacao getLicitacao() {
		return licitacao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedors;
	}

	public void setFornecedors(List<Fornecedor> fornecedors) {
		this.fornecedors = fornecedors;
	}

	
	public ItemLicitacao getItemLinhaEditavel() {
		return itemLinhaEditavel;
	}

	public void setItemLinhaEditavel(ItemLicitacao itemLinhaEditavel) {
		this.itemLinhaEditavel = itemLinhaEditavel;
	}

	public List<ItemLicitacao> completarProduto(String nome){
		
		if(this.requisicao.getLicitacao() != null && this.requisicao.getLicitacao().getId() != null){
			return this.itemLicitacaoRepository.porNome(nome, getRequisicao().getLicitacao().getId());
		}else{
			FacesUtil.addErrorMessage("Por favor! vincule o Contrato com o código chave!!");
		}
		
		return null;
		
	}
	
	public void carregaItemLicitacaoEditavel(){
		ItemRequisicao item = this.requisicao.getItens().get(0);
		
		if(this.itemLinhaEditavel != null){			
			
			if(this.existemItemComProduto(this.itemLinhaEditavel)){
				FacesUtil.addErrorMessage("Já existe o item " +this.getItemLinhaEditavel().getProduto().getNome()+ " nesta Requisição!");
			}
			
			else{
				
				item.setItemLicitacao(this.itemLinhaEditavel);
				item.setValorUnitario(itemLinhaEditavel.getVlrUnitario());
				item.setDataItem(new Date());
				
				this.requisicao.adicionarItemVazio();
				this.itemLinhaEditavel = null;
				this.recalcularRequisicao();
				
			}	
			
		}
		
	}

	private boolean existemItemComProduto(ItemLicitacao itemLinhaEditavel) {
		boolean existeItem = false;
		
		for(ItemRequisicao item : this.getRequisicao().getItens()){
			if(itemLinhaEditavel.getProduto().equals(item.getItemLicitacao().getProduto())){
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

	public void carregarLicitacaoPorChave() {
		if (getLicitacao().getId() != null) {
			this.licitacao = this.licitacaoRepository.porChave(this.getLicitacao().getId());
			if (this.licitacao != null && this.licitacao.getId() != null) {
				if (this.licitacao.getStatus().equals(StatusPedido.EMITIDO)) {
					this.requisicao.setLicitacao(this.licitacao);
					//this.licitacao = new Licitacao();
				} else {
					FacesUtil.addErrorMessage("Requisição não autorizada com Licitação no status "
							+ this.licitacao.getStatus().getDescricao());
					this.licitacao = new Licitacao();
					this.requisicao.setLicitacao(licitacao);
				}

			} else {
				FacesUtil
						.addErrorMessage("Não existe Licitação com esse Número de processo.");
				this.licitacao = new Licitacao();
				this.requisicao.setLicitacao(licitacao);
			}

		}
	}
	
	
	public void atualizarQuantidade(ItemRequisicao item, int linha){
		if(item.getQuantidadeItem() < 1){
			if(linha == 0){
				item.setQuantidadeItem(1);
			}else{
				this.getRequisicao().getItens().remove(linha);
				this.recalcularRequisicao();
			}
			
		}
		
		this.recalcularRequisicao();
	}
	
	
	public void recalcularRequisicao(){
		if(this.requisicao != null){
			this.requisicao.recalcularValorGlobal();
		}
	}
	
	
	public void imprimirRequisicao(){
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cod_requisicao", this.requisicao.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Requisicao.jasper", "Requisicao.pdf", this.response, parametros);
		
		Session session  = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if(executor.isRelatorioGerado()){
			facesContext.responseComplete();
		}else{
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
		
	}
	

}
