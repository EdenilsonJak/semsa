package com.ejsistemas.semsa.model.dvs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_atividade_economica")
public class AtividadeEconomica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long cdatividadeeconomica;
	private String nmatividade;
	private Date data;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCdatividadeeconomica() {
		return cdatividadeeconomica;
	}
	public void setCdatividadeeconomica(Long cdatividadeeconomica) {
		this.cdatividadeeconomica = cdatividadeeconomica;
	}
	
	@Column(length = 100)
	public String getNmatividade() {
		return nmatividade;
	}
	public void setNmatividade(String nmatividade) {
		this.nmatividade = nmatividade;
	}
	
	@Column
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdatividadeeconomica == null) ? 0 : cdatividadeeconomica.hashCode());
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
		AtividadeEconomica other = (AtividadeEconomica) obj;
		if (cdatividadeeconomica == null) {
			if (other.cdatividadeeconomica != null)
				return false;
		} else if (!cdatividadeeconomica.equals(other.cdatividadeeconomica))
			return false;
		return true;
	}
	
	

}
