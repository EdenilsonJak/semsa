package com.ejsistemas.semsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQueries({
@NamedQuery(name = "Procedimento.buscarProcedimentos", 
			query = "SELECT p FROM Procedimento p order by p.co_procedimento")
})


@Entity
@Table(name = "tb_procedimento")
public class Procedimento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 10)
	private String co_procedimento;
	@Column(length = 250)
	private String no_procedimento;
	@Column()
	private String tp_complexidade;
	@Column(length = 1)
	private String tp_sexo;
	@Column(length = 4)
	private Integer qt_maxima_execucao;
	@Column(length = 4)
	private Integer qt_dias_permanencia;
	@Column(length = 4)
	private Integer qt_pontos;
	@Column(length = 4)
	private Integer vl_idade_minima;
	@Column(length = 4)
	private Integer vl_idade_maxima;
	@Column(length = 10)
	private Integer vl_sh;
	@Column(length = 10)
	private Integer vl_sa;
	@Column(length = 10)
	private Integer vl_sp;
	@Column(length = 2)
	private String co_financiamento;
	@Column(length = 6)
	private String co_rubrica;
	@Column(length = 4)
	private Integer qt_tempo_permanencia;
	@Column(length = 6)
	private String dt_competencia;
	
	@Transient
	private String codNomeProcedimento;
	
	public String getCo_procedimento() {
		return co_procedimento;
	}
	public void setCo_procedimento(String co_procedimento) {
		this.co_procedimento = co_procedimento;
	}
	public String getNo_procedimento() {
		return no_procedimento;
	}
	public void setNo_procedimento(String no_procedimento) {
		this.no_procedimento = no_procedimento;
	}
		
	public String getTp_complexidade() {
		return tp_complexidade;
	}
	public void setTp_complexidade(String tp_complexidade) {
		this.tp_complexidade = tp_complexidade;
	}
	public String getTp_sexo() {
		return tp_sexo;
	}
	public void setTp_sexo(String tp_sexo) {
		this.tp_sexo = tp_sexo;
	}
	
	public Integer getQt_maxima_execucao() {
		return qt_maxima_execucao;
	}
	public void setQt_maxima_execucao(Integer qt_maxima_execucao) {
		this.qt_maxima_execucao = qt_maxima_execucao;
	}
	public Integer getQt_dias_permanencia() {
		return qt_dias_permanencia;
	}
	public void setQt_dias_permanencia(Integer qt_dias_permanencia) {
		this.qt_dias_permanencia = qt_dias_permanencia;
	}
	public Integer getQt_pontos() {
		return qt_pontos;
	}
	public void setQt_pontos(Integer qt_pontos) {
		this.qt_pontos = qt_pontos;
	}
	public Integer getVl_idade_minima() {
		return vl_idade_minima;
	}
	public void setVl_idade_minima(Integer vl_idade_minima) {
		this.vl_idade_minima = vl_idade_minima;
	}
	public Integer getVl_idade_maxima() {
		return vl_idade_maxima;
	}
	public void setVl_idade_maxima(Integer vl_idade_maxima) {
		this.vl_idade_maxima = vl_idade_maxima;
	}
	public Integer getVl_sh() {
		return vl_sh;
	}
	public void setVl_sh(Integer vl_sh) {
		this.vl_sh = vl_sh;
	}
	public Integer getVl_sa() {
		return vl_sa;
	}
	public void setVl_sa(Integer vl_sa) {
		this.vl_sa = vl_sa;
	}
	public Integer getVl_sp() {
		return vl_sp;
	}
	public void setVl_sp(Integer vl_sp) {
		this.vl_sp = vl_sp;
	}
	public Integer getQt_tempo_permanencia() {
		return qt_tempo_permanencia;
	}
	public void setQt_tempo_permanencia(Integer qt_tempo_permanencia) {
		this.qt_tempo_permanencia = qt_tempo_permanencia;
	}
	public String getCo_financiamento() {
		return co_financiamento;
	}
	public void setCo_financiamento(String co_financiamento) {
		this.co_financiamento = co_financiamento;
	}
	public String getCo_rubrica() {
		return co_rubrica;
	}
	public void setCo_rubrica(String co_rubrica) {
		this.co_rubrica = co_rubrica;
	}
	public String getDt_competencia() {
		return dt_competencia;
	}
	public void setDt_competencia(String dt_competencia) {
		this.dt_competencia = dt_competencia;
	}
	
	public String getCodNomeProcedimento() {
		return codNomeProcedimento;
	}
	public void setCodNomeProcedimento(String codNomeProcedimento) {
		this.codNomeProcedimento = codNomeProcedimento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Procedimento other = (Procedimento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Procedimento [co_procedimento=" + co_procedimento + ", no_procedimento=" + no_procedimento
				+ ", tp_complexidade=" + tp_complexidade + ", tp_sexo=" + tp_sexo + ", qt_maxima_execucao="
				+ qt_maxima_execucao + ", qt_dias_permanencia=" + qt_dias_permanencia + ", qt_pontos=" + qt_pontos
				+ ", vl_idade_minima=" + vl_idade_minima + ", vl_idade_maxima=" + vl_idade_maxima + ", vl_sh=" + vl_sh
				+ ", vl_sa=" + vl_sa + ", vl_sp=" + vl_sp + ", co_financiamento=" + co_financiamento + ", co_rubrica="
				+ co_rubrica + ", qt_tempo_permanencia=" + qt_tempo_permanencia + ", dt_competencia=" + dt_competencia
				+ "]";
	}
}
