package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.stella.bean.validation.CPF;

@NamedQuery(name = "Medico.buscarMedicos", query = "select m from Medico m order by m.nome")
@Entity
@Table(name = "tb_medico")
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String nr_sus = null;
	private String cpf = null;
	private Date nascimento;
	private Date dataCadastro;
	private String sexo;
	private String cep;
	private String tipo_logradouro;
	private String logradouro;
	private String numero;
	private String bairro;
	private String estado;
	private String cidade;
	private String celular;
	private String fixo;
	private String crm_numero;
	private Integer conselho_id;
	private Integer sg_uf_emsissor;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message = "não pode está em branco")
	@Column(nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column()
	public String getNr_sus() {
		return nr_sus;
	}

	public void setNr_sus(String nr_sus) {
		this.nr_sus = nr_sus;
	}

	@CPF()
	@Column(nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	@Column
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column
	public String getTipo_logradouro() {
		return tipo_logradouro;
	}

	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}

	@Column
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
		
	}

	@Column
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column
	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	@Column
	public String getCrm_numero() {
		return crm_numero;
	}

	public void setCrm_numero(String crm_numero) {
		this.crm_numero = crm_numero;
	}

	@Column
	public Integer getConselho_id() {
		return conselho_id;
	}

	public void setConselho_id(Integer conselho_id) {
		this.conselho_id = conselho_id;
	}

	@Column
	public Integer getSg_uf_emsissor() {
		return sg_uf_emsissor;
	}

	public void setSg_uf_emsissor(Integer sg_uf_emsissor) {
		this.sg_uf_emsissor = sg_uf_emsissor;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Transient
	public String getCpfFormatado() {
		String formato = "";
		return formato = getCpf().substring(0, 3) + "." + getCpf().substring(3, 6) + "." + getCpf().substring(6, 9)
				+ "-" + getCpf().substring(9, getCpf().length());
	}

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@PreUpdate
	@PrePersist
	public void toUpper(){
		if(this.nome != null){
			this.setNome(getNome().toUpperCase());
		}
		if(this.tipo_logradouro != null){
			this.setTipo_logradouro(getTipo_logradouro().toUpperCase());
		}
		if(this.logradouro != null){
			this.setLogradouro(getLogradouro().toUpperCase());
		}
		if(this.bairro != null){
			this.setBairro(getBairro().toUpperCase());
		}
		if(this.cidade != null){
			this.setCidade(getCidade().toUpperCase());
		}
		if(this.estado != null){
			this.setEstado(getEstado().toUpperCase());
		}
		
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
		Medico other = (Medico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
