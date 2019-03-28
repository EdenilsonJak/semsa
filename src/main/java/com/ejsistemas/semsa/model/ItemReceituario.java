package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_itens_receituario")
public class ItemReceituario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataRequisicao;
	private Date dataMarcacao;
	private Date dataCancelado;
	private Status_Movimento_Requisicao movimento_status = Status_Movimento_Requisicao.ESPERA;
	private Status_Prioridade_Requisicao prioridade_status = Status_Prioridade_Requisicao.NORMAL;
	private String obs;
	private String obs_marcacao;
	private String obs_cancelar;
	private Medico medico;
	private Receituario receituario;
	private Usuario usuarioMarcacao;
	private Usuario usuarioCancela;
	private Solicitacao solicitacao;
	private UnidadeSaude unidadeSaude;
	private String responsavelJudicial;
	private Date dataProcesso;
	private Long numeroProcesso;

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
	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataMarcacao() {
		return dataMarcacao;
	}

	public void setDataMarcacao(Date dataMarcacao) {
		this.dataMarcacao = dataMarcacao;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCancelado() {
		return dataCancelado;
	}

	public void setDataCancelado(Date dataCancelado) {
		this.dataCancelado = dataCancelado;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 100)
	public Status_Movimento_Requisicao getMovimento_status() {
		return movimento_status;
	}

	public void setMovimento_status(Status_Movimento_Requisicao movimento_status) {
		this.movimento_status = movimento_status;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 100)
	public Status_Prioridade_Requisicao getPrioridade_status() {
		return prioridade_status;
	}

	public void setPrioridade_status(Status_Prioridade_Requisicao prioridade_status) {
		this.prioridade_status = prioridade_status;
	}

	@Column(length = 1000)
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Column(length = 1000)
	public String getObs_marcacao() {
		return obs_marcacao;
	}

	public void setObs_marcacao(String obs_marcacao) {
		this.obs_marcacao = obs_marcacao;
	}

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "medico_id", nullable = false)
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "receituario_id", nullable = false)
	public Receituario getReceituario() {
		return receituario;
	}

	public void setReceituario(Receituario receituario) {
		this.receituario = receituario;
	}

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "solicitacao_id", nullable = false)
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "unidadeSaude_id", nullable = false)
	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Date getDataProcesso() {
		return dataProcesso;
	}

	public void setDataProcesso(Date dataProcesso) {
		this.dataProcesso = dataProcesso;
	}

	@Column(length = 200)
	public String getResponsavelJudicial() {
		return responsavelJudicial;
	}

	public void setResponsavelJudicial(String responsavelJudicial) {
		this.responsavelJudicial = responsavelJudicial;
	}

	@Column()
	public Long getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(Long numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	@OneToOne()
	@JoinColumn(name = "usuarioMarcao_id")
	public Usuario getUsuarioMarcacao() {
		return usuarioMarcacao;
	}

	public void setUsuarioMarcacao(Usuario usuarioMarcacao) {
		this.usuarioMarcacao = usuarioMarcacao;
	}

	@OneToOne()
	@JoinColumn(name = "usuarioCancela_id")
	public Usuario getUsuarioCancela() {
		return usuarioCancela;
	}

	public void setUsuarioCancela(Usuario usuarioCancela) {
		this.usuarioCancela = usuarioCancela;
	}

	@Column(length = 500)
	public String getObs_cancelar() {
		return obs_cancelar;
	}

	public void setObs_cancelar(String obs_cancelar) {
		this.obs_cancelar = obs_cancelar;
	}

	@Transient
	public boolean isExistente() {
		return getId() != null;
	}

	@Transient
	public boolean isMarcado() {
		return movimento_status.equals(Status_Movimento_Requisicao.MARCADO);
	}

	@Transient
	public boolean isCancelado() {
		return movimento_status.equals(Status_Movimento_Requisicao.CANCELADO);
	}

	@Transient
	public boolean isCanceladoOuMarcado() {
		return movimento_status.equals(Status_Movimento_Requisicao.MARCADO)
				|| movimento_status.equals(Status_Movimento_Requisicao.CANCELADO);
	}

	@Transient
	public boolean isEspera() {
		return movimento_status.equals(Status_Movimento_Requisicao.ESPERA);
	}

	@Transient
	public boolean isUnidadeAssociado() {
		return this.getUnidadeSaude() != null && this.getUnidadeSaude().getId() != null;
	}

	@Transient
	public boolean isUnidadeNaoAssociado() {
		return this.getUnidadeSaude().getId() == null;
	}

	@Transient
	public boolean isPrioridadeAcaoJudicial() {
		return getPrioridade_status().equals(Status_Prioridade_Requisicao.ACAOJUDICIAL);
	}

	@Transient
	public boolean isNormal() {
		return this.getPrioridade_status().equals(Status_Prioridade_Requisicao.NORMAL);
	}

	@Transient
	public boolean isIdoso() {
		return this.getPrioridade_status().equals(Status_Prioridade_Requisicao.IDOSO);
	}

	@Transient
	public boolean isDeficienteFisico() {
		return this.getPrioridade_status().equals(Status_Prioridade_Requisicao.DEFICIENTEFISICO);
	}

	@Transient
	public boolean isRiscodeVida() {
		return this.getPrioridade_status().equals(Status_Prioridade_Requisicao.RISCODEVIDA);
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
		ItemReceituario other = (ItemReceituario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
