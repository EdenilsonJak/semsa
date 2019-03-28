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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_requisicao")
public class Requisicao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataRequisicao;
	private Date dataEmissao;
	private String obs;
	private BigDecimal vlrTotal = BigDecimal.ZERO;
	private StatusPedido status = StatusPedido.INICIAL;
	private Licitacao licitacao = new Licitacao();
	private String obscancel;
	private List<ItemRequisicao> itens = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_requisicao", nullable = false)
	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	@Column(name = "obs_requisicao")
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Column(name = "vrlTotalRequisicao")
	public BigDecimal getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(BigDecimal vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "statusRequisicao", nullable = false, length = 20)
	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	@OneToOne
	@JoinColumn(name = "licitacaoIdRequisicao")
	public Licitacao getLicitacao() {
		return licitacao;
	}

	public void setLicitacao(Licitacao licitacao) {
		this.licitacao = licitacao;
	}

	@OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ItemRequisicao> getItens() {
		return itens;
	}

	public void setItens(List<ItemRequisicao> itens) {
		this.itens = itens;
	}
	
	@Column(name = "obsCancel")
	public String getObscancel() {
		return obscancel;
	}

	public void setObscancel(String obscancel) {
		this.obscancel = obscancel;
	}
	
	@Column()
	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
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
		Requisicao other = (Requisicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void removeItemVazio() {
		ItemRequisicao primeiroItem = this.getItens().get(0);

		if (primeiroItem != null) {
			this.getItens().remove(0);
		}

	}

	@Transient
	public boolean isEmitido() {
		return StatusPedido.EMITIDO.equals(this.getStatus());
	}

	@Transient
	public boolean isInicial() {
		return StatusPedido.INICIAL.equals(getStatus());
	}

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	public boolean isNaoExistente() {
		return !isExistente();
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isInicial();
	}

	@Transient
	public boolean isNaoCancelavel() {
		return !isCancelavel();
	}

	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.iscancelado();
	}

	@Transient
	public boolean iscancelado() {
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
	public boolean isNaoEmitido() {
		return !this.isEmitido();
	}
	
	@Transient
	public boolean isFornecedorAssociado(){
		return this.getLicitacao().getFornecedor().getFornecedor() != "" 
				&& this.getLicitacao().getFornecedor().getFornecedor() != null ;
	}

	public void adicionarItemVazio() {
		if (this.isInicial()) {
			ItemLicitacao itemLicitacao = new ItemLicitacao();
			
			ItemRequisicao item = new ItemRequisicao();
			item.setRequisicao(this);
			// item.setQuantidadeItem(1);
			item.setItemLicitacao(itemLicitacao);

			this.getItens().add(0, item);
		}

	}

	public void recalcularValorGlobal() {
		BigDecimal total = BigDecimal.ZERO;

		for (ItemRequisicao item : this.getItens()) {
			if (item.getItemLicitacao().getProduto() != null
					&& item.getItemLicitacao().getProduto().getId() != null) {
				total = total.add(item.getVlr_global());
				this.setVlrTotal(total);
			}
		}

	}

}
