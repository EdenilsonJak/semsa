package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.ejsistemas.semsa.service.NegocioException;

@Entity
@Table(name = "tb_itens_licitacao")
public class ItemLicitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCriacao;
	private Integer quantidade;
	private Integer quantidadeEstoque;
	private BigDecimal vlrUnitario;
	private Produto produto = new Produto();
	private Licitacao licitacao;
	private BigDecimal vlrTotal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "quantidade", nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@ManyToOne()
	@JoinColumn(name = "produto_id_itens_licitacao", nullable = false)
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@ManyToOne()
	@JoinColumn(name = "licitacao_id")
	public Licitacao getLicitacao() {
		return licitacao;
	}

	public void setLicitacao(Licitacao licitacao) {
		this.licitacao = licitacao;
	}
	
	@NotNull
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(BigDecimal vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	public BigDecimal getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(BigDecimal vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

	@Column(name="quantidadeEstoque", nullable = false)
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
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
		ItemLicitacao other = (ItemLicitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public boolean isProdutoAssociado() {
		return this.getProduto() != null && this.getProduto().getId() != null;
	}

	/*@Transient
	public boolean isFornecedorAssociado() {
		return this.getFornecedor() != null
				&& this.getFornecedor().getId() != null;
	}*/

	@Transient
	public BigDecimal getValorTotal() {
		return this.vlrTotal = this.getVlrUnitario().multiply(
				new BigDecimal(this.getQuantidade()));
	}

	public void baixarEstoque(Integer quantidadeItem) {
		int novaQuantidade = this.getQuantidadeEstoque() - quantidadeItem;
		
		if(novaQuantidade < 0){
			throw new NegocioException("Não há disponibilidade no estoque de "
					+ quantidadeItem+ " Itens " +this.getProduto().getNome()+ " do Processo " + this.getLicitacao().getNr_processo()+ ".");
		}
		
		this.setQuantidadeEstoque(novaQuantidade);
		
	}

	public void adicionarEstoque(Integer quantidadeItem) {
		this.setQuantidadeEstoque(getQuantidadeEstoque() + quantidadeItem);
		
	}
	
	

}
