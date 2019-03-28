package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQuery(name = "Leito.buscarLeitos", query = "select l from Leito l")
@Entity
@Table(name = "tb_leito")
public class Leito implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private String sexo;
	private String status;
	private Date dataCriacao;
	private Fornecedor hospital;
	private Medico medico;
	private Clinica clinica;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	
	@Column	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	
	@Column	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo.toUpperCase();
	}
	
	@Column
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	public Fornecedor getHospital() {
		return hospital;
	}
	public void setHospital(Fornecedor hospital) {
		this.hospital = hospital;
	}
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "clinica_id", nullable = false)
	public Clinica getClinica() {
		return clinica;
	}
	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Transient
	public String getClinicaLeito(){
		return this.getClinica().getNome()+" - "+this.getNome();
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
	public boolean isAusente(){
		return getStatus().equals("AUSENTE");
	}
	
	@Transient
	public boolean isInternado(){
		return getStatus().equals("OCUPADO");
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
		Leito other = (Leito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
