package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.caelum.stella.format.CPFFormatter;

@NamedQueries({
@NamedQuery(name="Paciente.findById", 
			query="SELECT p FROM Paciente p WHERE p.id_cliente = :id"),
@NamedQuery(name = "Paciente.buscarPacientes", 
			query = "SELECT p FROM Paciente p order by p.nome")
})


@Entity
@Table(name = "tb_paciente")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id_cliente;
	private String nome;
	private String nome_mae;
	private Date dt_nascimento;
	private String cpf;
	private String sus;
	private Date dataCriacao;
	private String sexo;
	private String complemento;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String phone1;
	private String phone2;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	@NotEmpty
	@Column(name = "nome", length = 500, nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(nullable = false, length= 500)
	public String getNome_mae() {
		return nome_mae;
	}
	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}
	
	@NotNull(message="é obrigatório")
	@Column
	@Temporal(TemporalType.DATE)
	public Date getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	
	@CPF
	@Column(nullable = false)
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Column
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Column
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Column
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Column
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
	@Column
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	@Column	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column	
	public String getSus() {
		return sus;
	}
	public void setSus(String sus) {
		this.sus = sus;
	}
	
	@Column
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	@Transient
	public String getCpfFormatado(){
		if(this.getCpf()!= null && this.getCpf() != ""){
			return new CPFFormatter().format(getCpf());
		}
		return "";
	}
	
	@Transient
	public boolean isNovo() {
		return getId_cliente() == null;
	}
	
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	@PreUpdate
	@PrePersist
	public void toUpper(){
		if(getNome() != null){
			setNome(getNome().toUpperCase());
		}
		if(getNome_mae() != null){
			setNome_mae(getNome_mae().toUpperCase());
		}
		if(getCidade() != null){
			setCidade(getCidade().toUpperCase());
		}
		if(getComplemento() != null){
			setComplemento(getComplemento().toUpperCase());
		}
		if(getBairro() != null){
			setBairro(getBairro().toUpperCase());
		}
		if(getLogradouro() != null){
			setLogradouro(getLogradouro().toUpperCase());
		}
		if(getTipoLogradouro() != null){
			setTipoLogradouro(getTipoLogradouro().toUpperCase());
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		return true;
	}

	
	
}
