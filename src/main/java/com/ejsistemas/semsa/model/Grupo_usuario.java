package com.ejsistemas.semsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_grupo")
public class Grupo_usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Usuario usuario;
	private Grupo grupo;
	private Modulo_Sistema sistema_recepcao = Modulo_Sistema.SEMACESSO;
	private Modulo_Sistema sistema_regulacao = Modulo_Sistema.SEMACESSO;
	private Modulo_Sistema sistema_licitacao = Modulo_Sistema.SEMACESSO;
	private Modulo_Sistema sistema_almoxarifado = Modulo_Sistema.SEMACESSO;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "grupo_id", nullable = false)
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
		
	
	@Column
	@Enumerated(EnumType.STRING)
	public Modulo_Sistema getSistema_recepcao() {
		return sistema_recepcao;
	}

	public void setSistema_recepcao(Modulo_Sistema sistema_recepcao) {
		this.sistema_recepcao = sistema_recepcao;
	}

	@Column
	@Enumerated(EnumType.STRING)
	public Modulo_Sistema getSistema_regulacao() {
		return sistema_regulacao;
	}

	public void setSistema_regulacao(Modulo_Sistema sistema_regulacao) {
		this.sistema_regulacao = sistema_regulacao;
	}

	@Column
	@Enumerated(EnumType.STRING)
	public Modulo_Sistema getSistema_licitacao() {
		return sistema_licitacao;
	}

	public void setSistema_licitacao(Modulo_Sistema sistema_licitacao) {
		this.sistema_licitacao = sistema_licitacao;
	}
	
	@Column
	@Enumerated(EnumType.STRING)
	public Modulo_Sistema getSistema_almoxarifado() {
		return sistema_almoxarifado;
	}

	public void setSistema_almoxarifado(Modulo_Sistema sistema_almoxarifado) {
		this.sistema_almoxarifado = sistema_almoxarifado;
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
		Grupo_usuario other = (Grupo_usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
