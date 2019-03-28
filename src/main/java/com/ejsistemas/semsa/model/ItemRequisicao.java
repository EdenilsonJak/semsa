package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_itens_requisicao")
public class ItemRequisicao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dataItem;
	private Integer quantidadeItem = 1;
	private Requisicao requisicao;
	private ItemLicitacao itemLicitacao = new ItemLicitacao();
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getDataItem() {
		return dataItem;
	}
	public void setDataItem(Date dataItem) {
		this.dataItem = dataItem;
	}
	
	@NotNull
	@Column(nullable = false)
	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}
	public void setQuantidadeItem(Integer quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}
	
	@ManyToOne()
	@JoinColumn(name = "requisicao_id_itemrequisicao", nullable = false)
	public Requisicao getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	
	@NotNull
	@Column(nullable = false)
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	@NotNull
	@Column(nullable = false)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name="itemLicitacao_id", nullable = false)
	public ItemLicitacao getItemLicitacao() {
		return itemLicitacao;
	}
	public void setItemLicitacao(ItemLicitacao itemLicitacao) {
		this.itemLicitacao = itemLicitacao;
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
		ItemRequisicao other = (ItemRequisicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public boolean isProdutoAssociado() {
		return this.getItemLicitacao().getProduto() != null && this.getItemLicitacao().getProduto().getId() != null;
	}
	
	@Transient
	public boolean isEstoqueSuficiente() {
		return this.getRequisicao().isEmitido()
				|| this.getItemLicitacao().getProduto().getId() == null 
				|| this.getItemLicitacao().getQuantidadeEstoque() >= this.getQuantidadeItem();	
	}
	
	@Transient
	public boolean isEstoqueInsuficiente() {
		return !isEstoqueSuficiente();
	}
	
	@Transient
	public BigDecimal getVlr_global() {
		return this.valorTotal = this.getValorUnitario().multiply(
				new BigDecimal(this.getQuantidadeItem()));
	}
	
}
