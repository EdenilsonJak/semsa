package com.ejsistemas.semsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cid")
public class Cid10 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column()
	private String subcat;
	@Column()
	private String classif;
	@Column()
	private String restrsexo;
	@Column
	private String causaobito;
	@Column(length = 500)
	private String descricao;
	@Column(length = 600)
	private String descrabrev;
	@Column(length = 200)
	private String refer;
	@Column(length = 300)
	private String excluidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubcat() {
		return subcat;
	}

	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}

	public String getClassif() {
		return classif;
	}

	public void setClassif(String classif) {
		this.classif = classif;
	}

	public String getRestrsexo() {
		return restrsexo;
	}

	public void setRestrsexo(String restrsexo) {
		this.restrsexo = restrsexo;
	}

	public String getCausaobito() {
		return causaobito;
	}

	public void setCausaobito(String causaobito) {
		this.causaobito = causaobito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescrabrev() {
		return descrabrev;
	}

	public void setDescrabrev(String descrabrev) {
		this.descrabrev = descrabrev;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getExcluidos() {
		return excluidos;
	}

	public void setExcluidos(String excluidos) {
		this.excluidos = excluidos;
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
		Cid10 other = (Cid10) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
