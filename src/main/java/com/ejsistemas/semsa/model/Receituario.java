package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "tb_receituario")
public class Receituario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data;
	private Usuario usuarioEntrada;
	private Paciente paciente;
	private List<ItemReceituario> itens = new ArrayList<ItemReceituario>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name="usuarioEntrada_id", nullable = false)
	public Usuario getUsuarioEntrada() {
		return usuarioEntrada;
	}
	public void setUsuarioEntrada(Usuario usuarioEntrada) {
		this.usuarioEntrada = usuarioEntrada;
	}
	
	@NotNull(message = "é obrigatório")
	@ManyToOne()
	@JoinColumn(name = "paciente_id", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@OneToMany(mappedBy = "receituario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ItemReceituario> getItens() {
		return itens;
	}
	public void setItens(List<ItemReceituario> itens) {
		this.itens = itens;
	}
	
	@Transient
	public boolean isInicial(){
		return this.getId() == null;
	}
	
	@Transient
	public boolean isPacienteAssociado(){
		return this.getPaciente() != null;
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
		Receituario other = (Receituario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
