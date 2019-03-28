package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_licitacao")
public class Licitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer nr_processo;
	private Integer nr_publicacao;
	private String nomePeriodico;
	private Date dataLicitacao;
	private Date dt_publicacao;
	private Date dt_emissao;
	private String objeto;
	private String observacao;
	private BigDecimal valor_global = BigDecimal.ZERO;
	private TipoLicitacao tipolicitacao;
	private Publicacao publicacao;
	private ModalidadeLicitacao modalidade;
	private Fornecedor fornecedor = new Fornecedor();
	private StatusPedido status = StatusPedido.INICIAL;
	private List<ItemLicitacao> itens = new ArrayList<>();


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "nr_processo")
	public Integer getNr_processo() {
		return nr_processo;
	}

	public void setNr_processo(Integer nr_processo) {
		this.nr_processo = nr_processo;
	}

	@Column(name = "nr_publicacao")
	public Integer getNr_publicacao() {
		return nr_publicacao;
	}

	public void setNr_publicacao(Integer nr_publicacao) {
		this.nr_publicacao = nr_publicacao;
	}
	
	@Size(max = 200)
	@Column(name = "nm_periodico", length = 200)
	public String getNomePeriodico() {
		return nomePeriodico;
	}

	public void setNomePeriodico(String nomePeriodico) {
		this.nomePeriodico = nomePeriodico;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_licitacao", nullable = false)
	public Date getDataLicitacao() {
		return dataLicitacao;
	}

	public void setDataLicitacao(Date dataLicitacao) {
		this.dataLicitacao = dataLicitacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_publicacao")
	public Date getDt_publicacao() {
		return dt_publicacao;
	}

	public void setDt_publicacao(Date dt_publicacao) {
		this.dt_publicacao = dt_publicacao;
	}

	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@NotNull
	@Column(columnDefinition = "text")
	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	@NotNull
	@Column(nullable = false)
	public BigDecimal getValor_global() {
		return valor_global;
	}

	public void setValor_global(BigDecimal valor_global) {
		this.valor_global = valor_global;
	}

	@OneToMany(mappedBy = "licitacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ItemLicitacao> getItens() {
		return itens;
	}

	public void setItens(List<ItemLicitacao> itens) {
		this.itens = itens;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	@ManyToOne()
	@JoinColumn(name = "publicacao_id")
	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "modalidade_id", nullable = false)
	public ModalidadeLicitacao getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeLicitacao modalidade) {
		this.modalidade = modalidade;
	}

	@ManyToOne()
	@JoinColumn(name = "tipolicitacao_id")
	public TipoLicitacao getTipolicitacao() {
		return tipolicitacao;
	}

	public void setTipolicitacao(TipoLicitacao tipolicitacao) {
		this.tipolicitacao = tipolicitacao;
	}
	
	@ManyToOne()
	@JoinColumn(name = "fornecedor_id")
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Column()
	public Date getDt_emissao() {
		return dt_emissao;
	}

	public void setDt_emissao(Date dt_emissao) {
		this.dt_emissao = dt_emissao;
	}

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	@Transient
	public boolean isNaoExistente(){
		return !isExistente();
	}
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Licitacao other = (Licitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void adicionarItemVazio() {
		if (this.isInicial()) {

			Produto produto = new Produto();
			//Fornecedor fornecedor = new Fornecedor();

			ItemLicitacao item = new ItemLicitacao();
			item.setProduto(produto);
			item.setLicitacao(this);
			item.setQuantidade(1);
			item.setQuantidadeEstoque(1);
			item.setVlrUnitario(BigDecimal.ZERO);
			this.getItens().add(0, item);
		}

	}

	@Transient
	public boolean isInicial() {
		return StatusPedido.INICIAL.equals(getStatus());
	}
	
	@Transient
	public boolean isAtualizavel(){
		return isInicial() && !isNovo();
	}

	public void recalcularValorGlobal() {
		BigDecimal total = BigDecimal.ZERO;

		for (ItemLicitacao item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getId() != null) {
				total = total.add(item.getValorTotal());
			}
		}

		this.setValor_global(total);
	}

	@Transient
	public boolean isOrcamento() {
		return StatusPedido.ORCAMENTO.equals(this.getStatus());
	}

	public void removerItemVazio() {
		ItemLicitacao primeiroItem = this.getItens().get(0);

		if (primeiroItem != null && primeiroItem.getProduto().getId() == null) {
			this.getItens().remove(0);
		}
	}

	@Transient
	public boolean isValorTotalNegativo() {
		return this.getValor_global().compareTo(BigDecimal.ZERO) < 0;
	}

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isInicial();
	}
	
	@Transient
	public boolean isNaoCancelavel(){
		return !isCancelavel();
	}

	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}

	@Transient
	public boolean isCancelado() {
		return StatusPedido.CANCELADO.equals(this.getStatus());
	}

	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}

	@Transient
	public boolean isAlteravel() {
		return this.isInicial();
	}
	
	@Transient
	public boolean isEmitido(){
		return StatusPedido.EMITIDO.equals(this.getStatus());
	}

}
