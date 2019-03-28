package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_internacao")
public class Internacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cod_controle;
	private LocalDateTime dataInternacao;
	private LocalDateTime dataAlta;
	private LocalDateTime dataCancelamento;
	private String status;
	private String obs_cancela;
	private String obs_interncao;
	private String obs_alta;
	private Paciente paciente;
	private Leito leito;
	private Medico medicoSolicitante;
	private Medico medico_responsavel_leito;
	private String motivo;
	private String motivoInterncao;
	private Internacao internacao;
	private Usuario usuario;
	private Usuario usuarioCancelador;
	private Usuario usuarioAlta;
	private Cid10 cid;
	private List<Internacao> itens = new ArrayList<Internacao>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	@Column
	public LocalDateTime getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(LocalDateTime dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	@Column
	public LocalDateTime getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(LocalDateTime dataAlta) {
		this.dataAlta = dataAlta;
	}

	@Column
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(length = 1000)
	public String getObs_cancela() {
		return obs_cancela;
	}

	public void setObs_cancela(String obs_cancela) {
		this.obs_cancela = obs_cancela;
	}

	@Column(length = 1000)
	public String getObs_interncao() {
		return obs_interncao;
	}

	public void setObs_interncao(String obs_interncao) {
		this.obs_interncao = obs_interncao;
	}

	@Column(length = 500)
	public String getObs_alta() {
		return obs_alta;
	}

	public void setObs_alta(String obs_alta) {
		this.obs_alta = obs_alta;
	}

	@NotNull(message = "é obrigatório")
	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@ManyToOne
	@JoinColumn(name = "leito_id", nullable = false)
	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	@ManyToOne
	@JoinColumn(name = "medicoSolicitante_id")
	public Medico getMedicoSolicitante() {
		return medicoSolicitante;
	}

	public void setMedicoSolicitante(Medico medicoSolicitante) {
		this.medicoSolicitante = medicoSolicitante;
	}

	@ManyToOne
	@JoinColumn(name = "medico_responsavel_leito_id")
	public Medico getMedico_responsavel_leito() {
		return medico_responsavel_leito;
	}

	public void setMedico_responsavel_leito(Medico medico_responsavel_leito) {
		this.medico_responsavel_leito = medico_responsavel_leito;
	}

	@ManyToOne
	@JoinColumn(name = "fk_internacao_id")
	public Internacao getInternacao() {
		return internacao;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	@Column
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Column
	public String getMotivoInterncao() {
		return motivoInterncao;
	}

	public void setMotivoInterncao(String motivoInterncao) {
		this.motivoInterncao = motivoInterncao;
	}

	@OneToMany(mappedBy = "internacao", orphanRemoval = true, fetch=FetchType.LAZY)
	public List<Internacao> getItens() {
		return itens;
	}

	public void setItens(List<Internacao> itens) {
		this.itens = itens;
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
	@JoinColumn(name = "usuario_cancelador_id")
	public Usuario getUsuarioCancelador() {
		return usuarioCancelador;
	}

	public void setUsuarioCancelador(Usuario usuarioCancelador) {
		this.usuarioCancelador = usuarioCancelador;
	}

	@ManyToOne
	@JoinColumn(name = "usuario_alta_id")
	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}
	
	@Column()
	public String getCod_controle() {
		return cod_controle;
	}

	public void setCod_controle(String cod_controle) {
		this.cod_controle = cod_controle;
	}
	
	@ManyToOne()
	@JoinColumn(name = "cid_id")
	public Cid10 getCid() {
		return cid;
	}

	public void setCid(Cid10 cid) {
		this.cid = cid;
	}

	@Transient
	public String getControle(){
		if(getCod_controle() != null){
			return getCod_controle()+"."+getId();
		}
		return "";
	}

	@Transient
	public boolean isNovo() {
		return this.getId() == null;
	}

	@Transient
	public boolean isOcupado() {
		return this.getStatus().equals("OCUPADO");
	}

	@Transient
	public boolean isExistente() {
		return !this.isNovo();
	}

	@Transient
	public boolean isAusente() {
		return this.getStatus().equals("AUSENTE");
	}

	@Transient
	public boolean isTransferido() {
		return this.getStatus().equals("TRANSFERIDO");
	}

	@Transient
	public boolean isCancelado() {
		return isExistente() && this.getStatus().equals("CANCELADO");
	}

	@Transient
	public boolean isAlta() {
		return isExistente() && this.getStatus().equals("ALTA");
	}
	
	@PreUpdate
	@PrePersist
	public void toUpper(){
		if(obs_alta != null){
			this.setObs_alta(getObs_alta().toUpperCase());
		}
		if(obs_cancela != null){
			this.setObs_cancela(getObs_cancela().toUpperCase());
		}
		if(obs_interncao != null){
			this.setObs_interncao(getObs_interncao().toUpperCase());
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
		Internacao other = (Internacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
