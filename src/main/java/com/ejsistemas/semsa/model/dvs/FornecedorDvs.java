package com.ejsistemas.semsa.model.dvs;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.caelum.stella.format.CNPJFormatter;

@Entity
@Table(name = "tb_fornecedor")
public class FornecedorDvs implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long cdfornecedor;
	private String fornecedor;
	private String fantasia;
	private String cnpj;
	private String insc_estadual;
	private Date data;
	private String tipoLogradouro;
	private String logradouro;
	private Integer numero;
	private String bairro;
	private String perimetro;
	private String cep;
	private String estado;
	private String cidade;
	private String phone;
	private String phonefax;
	private String celular;
	private String status;
	private AtividadeEconomica atividadeeconomica;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCdfornecedor() {
		return cdfornecedor;
	}
	public void setCdfornecedor(Long cdfornecedor) {
		this.cdfornecedor = cdfornecedor;
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
	@Column(name = "data", nullable = false)	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	@Column(length = 14)	
	public String getPhonefax() {
		return phonefax;
	}
	public void setPhonefax(String phonefax) {
		this.phonefax = phonefax;
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
	
	@ManyToOne()
	@JoinColumn(name="atividadeeconomica")
	public AtividadeEconomica getAtividadeeconomica() {
		return atividadeeconomica;
	}
	public void setAtividadeeconomica(AtividadeEconomica atividadeeconomica) {
		this.atividadeeconomica = atividadeeconomica;
	}
	
	@Transient
	public String getCnpjFormatado(){
		return new CNPJFormatter().format(getCnpj());
	}
	
	@Transient
	public boolean isNovo(){
		return getCdfornecedor() == null;
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
		result = prime * result + ((cdfornecedor == null) ? 0 : cdfornecedor.hashCode());
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
		FornecedorDvs other = (FornecedorDvs) obj;
		if (cdfornecedor == null) {
			if (other.cdfornecedor != null)
				return false;
		} else if (!cdfornecedor.equals(other.cdfornecedor))
			return false;
		return true;
	}
	
	
	
}
