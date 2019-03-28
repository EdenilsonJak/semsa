package com.ejsistemas.semsa.controller.licitacao;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.ejsistemas.semsa.event.LicitacaoAlteradoEvent;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.ItemLicitacao;
import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.model.ModalidadeLicitacao;
import com.ejsistemas.semsa.model.Produto;
import com.ejsistemas.semsa.model.Publicacao;
import com.ejsistemas.semsa.model.TipoLicitacao;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.ModalidadeLicitacaoRepository;
import com.ejsistemas.semsa.repository.ProdutoRepository;
import com.ejsistemas.semsa.repository.PublicacaoRepository;
import com.ejsistemas.semsa.repository.TipoLicitacaoRepository;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.service.CadastroLicitacaoService;
import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class CadastroLicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FornecedorRepository fornecedorRepository;

	@Inject
	private ProdutoRepository produtoRepository;

	@Inject
	private ModalidadeLicitacaoRepository modalidadeLicitacaoRepository;

	@Inject
	PublicacaoRepository publicacaoRepository;

	@Inject
	private TipoLicitacaoRepository tipoLicitacaoRepository;

	@Inject
	private CadastroLicitacaoService cadastroLicitacaoService;
	
	//@Inject
	//private SetoresRepository setoresRepository;

	@Produces
	@LicitacaoEdicao
	private Licitacao licitacao;
	
	private ItemLicitacao itemLicitacao;
	
	private Produto produtoLinhaEditavel;
	private Fornecedor fornecedorLinhaEditavel;
	//private Setores setoresLinhaEditavel;

	private List<ModalidadeLicitacao> modalidades;
	private List<TipoLicitacao> tipoLicitacoes;
	private List<Fornecedor> fornecedores;

	public CadastroLicitacaoBean() {
		this.limpar();
	}
	

	public void licitacaoAlterado(@Observes LicitacaoAlteradoEvent event){
		this.licitacao = event.getLicitacao();
	}
	
	public Fornecedor getFornecedorLinhaEditavel() {
		return fornecedorLinhaEditavel;
	}

	public void setFornecedorLinhaEditavel(Fornecedor fornecedorLinhaEditavel) {
		this.fornecedorLinhaEditavel = fornecedorLinhaEditavel;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public void inicializar() {
			this.modalidades = this.modalidadeLicitacaoRepository.raizes();
			this.tipoLicitacoes = this.tipoLicitacaoRepository.raizes();
			this.fornecedores = this.fornecedorRepository.filtrados(new FornecedorFilter());
			this.licitacao.adicionarItemVazio();
			this.recalcularLicitacao();
	}

	public void limpar() {
		this.licitacao = new Licitacao();
	}

	public void salvar() {
		this.licitacao.removerItemVazio();
		try{
			if(this.licitacao.isExistente()){
				this.licitacao = this.cadastroLicitacaoService
						.salvar(this.licitacao);
				FacesUtil.addInfoMessage("Contrato atualizado com sucesso!");
			}else{
				this.licitacao = this.cadastroLicitacaoService
					.salvar(this.licitacao);

			FacesUtil.addInfoMessage("Contrato cadastrada com sucesso!");
			}
			
		} finally {
			this.licitacao.adicionarItemVazio();
		}
	}

	public void carregarProdutoPorId() {
		if (getLicitacao().getItens().get(0).getProduto().getId() != null) {
			this.produtoLinhaEditavel = this.produtoRepository.porId(getLicitacao().getItens().get(0).getProduto().getId());
			if(this.produtoLinhaEditavel != null){
				this.carregarProdutoLinhaEditavel();
			} else{
				FacesUtil.addErrorMessage("Não existe item com esse Código.");
				getLicitacao().getItens().remove(0);
				this.licitacao.adicionarItemVazio();	
			}
			
		}
	}

	/*public void carregarFornecedorLinhaEditavel() {
		ItemLicitacaFornecedor item = this.licitacao.getItens().get(0);

		if (fornecedorLinhaEditavel != null) {
			if(existeItemComFornecedor(fornecedorLinhaEditavel, produtoLinhaEditavel)){
				FacesUtil.addErrorMessage("Ja existe item com esse fornecedor!");
			} else{
				
			item.setFornecedor(fornecedorLinhaEditavel);
			//item.setQuantidadeEstoque(item.getQuantidade());
			produtoLinhaEditavel = null;
			fornecedorLinhaEditavel = null;
			this.licitacao.adicionarItemVazio();
		}
	}
}*/
	
	/*public void carregarSetorLinhaEditavel(){
		for (ItemLicitacaFornecedor item : this.getLicitacao().getItens()) {
			if (itemLicitacaFornecedor.equals(item)) {
				item.setSetors(this.itemLicitacaFornecedor.getSetors());
				//this.licitacao.getItens().remove(0);
				//this.licitacao.adicionarItemVazio();
				//break;
			}
		}
		//return existemItemCfornecedor; 
		
		
	}*/

	public void carregarProdutoLinhaEditavel() {
		ItemLicitacao item = this.licitacao.getItens().get(0);
		
		if (produtoLinhaEditavel != null) {
			if(existeItem(produtoLinhaEditavel)){
				FacesUtil.addAlerMessage("Item já existente, favor verifique!");
			}else{
				item.setProduto(produtoLinhaEditavel);
				item.setDataCriacao(new Date());
				produtoLinhaEditavel = new Produto();
				//item.setQuantidadeEstoque(item.getQuantidade());
				//item.setQuantidadeEstoque(item.getQuantidade());
				this.licitacao.adicionarItemVazio();
			}
				
	}
}
		private boolean existeItem(Produto produtoLinhaEditavel2) {
				boolean existemItem = false;
				for(ItemLicitacao item: this.getLicitacao().getItens()){
					if(item.getProduto().equals(produtoLinhaEditavel2)){
						existemItem = true;
						this.licitacao.getItens().remove(0);
						this.licitacao.adicionarItemVazio();
						break;
					}
				}
				return existemItem;
			}


	/*
	private boolean existeItemComFornecedor(Fornecedor fornecedor, Produto produto) {
		boolean existemItemCfornecedor = false;
		for (itemLicitaca item : this.getLicitacao().getItens()) {
			if (fornecedor.equals(item.getFornecedor()) && produto.equals(item.getProduto())) {
				existemItemCfornecedor = true;
				this.licitacao.getItens().remove(0);
				this.licitacao.adicionarItemVazio();
				break;
			}
		}
		return existemItemCfornecedor;
	}
*/
	public List<Publicacao> completarPublicacao(String nome) {
		return this.publicacaoRepository.porNome(nome);
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtoRepository.porNome(nome);
	}

	public List<Fornecedor> completarFornecedor(String nome) {
		return this.fornecedorRepository.porNome(nome);
	}
	
	/*public List<Setores> completarSetores(String nome){
		return this.setoresRepository.porNome(nome);
	}*/

	public Licitacao getLicitacao() {
		return licitacao;
	}

	public void setLicitacao(Licitacao licitacao) {
		this.licitacao = licitacao;
	}

	public void atualizarQuantidade(ItemLicitacao item, int linha){
		if(item.getQuantidade() == null){
			this.licitacao.getItens().get(linha).setQuantidade(1);
			this.licitacao.getItens().get(linha).setQuantidadeEstoque(1);
			recalcularLicitacao();
		}else{
			if(item.getQuantidade() < 1 ){
			if(linha == 0){
				item.setQuantidade(1);
				item.setProduto(new Produto());
				item.setVlrUnitario(BigDecimal.ZERO);
				item.setVlrTotal(BigDecimal.ZERO);
				recalcularLicitacao();
			} else{
				this.getLicitacao().getItens().remove(linha);
				recalcularLicitacao();
			}
		}
			else{
				item.setQuantidadeEstoque(item.getQuantidade());
				recalcularLicitacao();
			}
		}
		
		}
		
	
	public void incluirUnidadeSolicitante(){
		
	}
	
	
	/*public FormaPagamento[] getFormasPagamentos() {
		return FormaPagamento.values();
	}*/

	public boolean isEditando() {
		return this.licitacao.getId() != null;
	}

	public List<ModalidadeLicitacao> getModalidades() {
		return modalidades;
	}

	public List<TipoLicitacao> getTipoLicitacoes() {
		return tipoLicitacoes;
	}
	
	public void recalcularLicitacao(){
		if(this.licitacao != null ){
		this.licitacao.recalcularValorGlobal();
		
		}
	}
	
	public void imprimirLicitacao(){
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cod_licitacao", this.licitacao.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Contrato.jasper", "Relatorio_Contrato.pdf", this.response, parametros);
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if(executor.isRelatorioGerado()){
			facesContext.responseComplete();
		}else{
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
		
	}

	public ItemLicitacao getItemLicitacao() {
		return itemLicitacao;
	}

	public void setItemLicitacao(ItemLicitacao itemLicitacao) {
		this.itemLicitacao = itemLicitacao;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}


	/*public Setores getSetoresLinhaEditavel() {
		return setoresLinhaEditavel;
	}*/


	/*public void setSetoresLinhaEditavel(Setores setoresLinhaEditavel) {
		this.setoresLinhaEditavel = setoresLinhaEditavel;
	}*/
	
	

}
