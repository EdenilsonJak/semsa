package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.caelum.stella.format.CNPJFormatter;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String fornecedor;
	private String fantasia;
	private String cnpj;
	private String insc_estadual;
	private Date dataCriacao;
	private String tipoLogradouro;
	private String logradouro;
	private Integer numero;
	private String bairro;
	private String perimetro;
	private String cep;
	private String estado;
	private String cidade;
	private String phone;
	private String fax;
	private String celular;
	private String status;
	private TipoEstabelecimento tipoEstabelecimento;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {	
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Size(max = 200)
	@Column(name = "nm_fornecedor", nullable = false, length = 200)	
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor.toUpperCase();
	}
	
	@Size(max = 200)
	@Column(name = "nm_fantasia", nullable = false, length = 200)
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia.toUpperCase();
	}
	
	@NotNull
	@CNPJ
	@Size(max = 14)
	@Column(name = "cnpj", nullable = false, length = 14)
	public String getCnpj() {		
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Column(name = "insc_estadual", length = 100)
	public String getInsc_estadual() {
		return insc_estadual;
	}
	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(length = 200, nullable = false)
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro.toUpperCase();
	}
	
	@Size(max = 200)
	@Column(name = "logradouro", nullable = false, length = 200)
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.toUpperCase();
	}
	
	@Column(name = "numero")
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	@NotEmpty
	@Size(max = 200)
	@Column(name = "nm_bairro", nullable = false, length = 200)
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}
	
	@Size(max = 300)
	@Column(name = "nm_perimetro", length = 300)
	public String getPerimetro() {
		return perimetro;
	}
	public void setPerimetro(String perimetro) {
		this.perimetro = perimetro;
	}
	
	@Size(max = 8)
	@Column(name = "cep", nullable = false, length = 8)
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Size(max = 14)
	@Column(name = "phone", length = 14)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Size(max = 14)
	@Column(name = "fax", length = 14)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name = "estado", nullable = false)
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado.toUpperCase();
	}
	
	@Column(name = "cidade", nullable = false)
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase();
	}
	
	@Column(name = "celular", length = 14)	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Column()	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public String getCnpjFormatado(){
		return new CNPJFormatter().format(getCnpj());
	}
	
	@Column(length = 100, nullable = false)
	@Enumerated(EnumType.STRING)
	public TipoEstabelecimento getTipoEstabelecimento() {
		return tipoEstabelecimento;
	}
	public void setTipoEstabelecimento(TipoEstabelecimento tipoEstabelecimento) {
		this.tipoEstabelecimento = tipoEstabelecimento;
	}
	
	@Transient
	public boolean isNovo(){
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente(){
		return !isNovo();
	}
	
	@Transient
	public String getCepFormatado() {
	    String formato = "";
	    	return formato = getCep().substring(0, 2)+"."+getCep().substring(2, 5)+"-"+getCep().substring(5, getCep().length()); 
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
		Fornecedor other = (Fornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
