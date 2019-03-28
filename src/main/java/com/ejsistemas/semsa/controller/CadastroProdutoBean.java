package com.ejsistemas.semsa.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.ejsistemas.semsa.model.Apresentacao;
import com.ejsistemas.semsa.model.Categoria;
import com.ejsistemas.semsa.model.Produto;
import com.ejsistemas.semsa.model.UnidadeMedida;
import com.ejsistemas.semsa.repository.ApresentacaoRepository;
import com.ejsistemas.semsa.repository.CategoriaRepository;
import com.ejsistemas.semsa.repository.UnidadeMedidaRepository;
import com.ejsistemas.semsa.service.CadastroProdutoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UnidadeMedida> unidadeMedida;
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	private List<Produto> produtos;
	private List<Apresentacao> apresentacoes;

	private Produto produtoEdicao;
	private Produto produto;
	private Categoria categoriaPai;
	private int activeIndex;
	private String arquivo = "Digite o nome do arquivo";

	@Inject
	private UnidadeMedidaRepository unidadeMedidaRepository;

	@Inject
	private ApresentacaoRepository apresentacaoRepository;
	
	@Inject
	private CategoriaRepository categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {
			this.unidadeMedida = this.unidadeMedidaRepository.unidadeMedidas();
			categoriasRaizes = categorias.raizes();
			this.apresentacoes = this.apresentacaoRepository.raizes();
		if (this.categoriaPai != null) {
			carregarSubcategorias();
		}
		// this.produtos = this.produtoRepository.filtrados(new
		// ProdutoFilter());

	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(produto);
		limpar();
		// throw new NegocioException("Pedido nÃ£o pode ser salvo, pois ainda
		// nÃ£o foi implementado!!");

		FacesUtil.addInfoMessage("Produto cadastrado com Sucesso!");
	}

	private void limpar() {
		produto = new Produto();
		produtoEdicao = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
		//produtos = new ArrayList<>();
	}

	public List<UnidadeMedida> getUnidadeMedida() {
		return unidadeMedida;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProdutoEdicao() {
		return produtoEdicao;
	}

	public void setProdutoEdicao(Produto produtoEdicao) {
		this.produtoEdicao = produtoEdicao;
	}
	
	public List<Apresentacao> getApresentacoes() {
		return apresentacoes;
	}

	public void onSelect() {
		this.setProduto(this.produtoEdicao);
		this.produtoEdicao = new Produto();

	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public void goToTab(int index) {
		this.activeIndex = index;
	}

	public void fazBackup() throws IOException{
		
		Date data = new Date();
		String formatData = new SimpleDateFormat("dd-MM-YYYY").format(data);
		
		MysqlDumputils.dump("C:/bd_santarosa/bd_santarosa_"+formatData+".sql", "root", "root", "bd_santarosa", "") ;		
//		String commando = FacesContext.getCurrentInstance().getExternalContext().getRealPath("" +"/WEB-INF/classes/backup/mysql.bat");
//		 String line;
//	      Process p = Runtime.getRuntime().exec(commando);
//	      BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
//	      while ((line = input.readLine()) != null) {
//	        System.out.println(line);
//	      }
//	      input.close();
//	      FacesUtil.addInfoMessage("O arquivo de backup encontra-se na raiz do computador na pasta bd_santarosa com a data de hoje!");
//		
	}

	public void restoreBackup() throws IOException {
		
		MysqlDumputils.restore("C:/bd_santarosa/"+getArquivo(), "localhost", "3306", "root", "root", "bd_santarosa");
		
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}	
	
	
	
}
