package com.ejsistemas.semsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.caelum.stella.format.CPFFormatter;

@NamedQueries({ @NamedQuery(name = "Pericia.buscarPericias", query = "SELECT p FROM Pericia p order by p.dt_entrada") })

@Entity
@Table(name = "tb_pericia")
public class Pericia implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id_pericia;
	private Date dt_entrada;
	private Date dt_entrega;
	private Status_Pericia status = Status_Pericia.RECEBIDO;
	private Medico medico;
	private Cid10 cid;
	private String co_procedimento;
	private String no_procedimento;
	private Fornecedor estabelecimento;
	private Fornecedor estabelecimentoDestino;
	private Paciente paciente;
	private Usuario usuario_entrada;
	private Usuario usuario_saida;
	private String nomeRecebor;
	private String rgCpf;
	private StatusTipoDocumento doc;
	private String contatoCel;
	private String contatoCel1;
	private String obs_entrega;
	private String obs_entrada;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_pericia() {
		return id_pericia;
	}

	public void setId_pericia(Long id_pericia) {
		this.id_pericia = id_pericia;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDt_entrada() {
		return dt_entrada;
	}

	public void setDt_entrada(Date dt_entrada) {
		this.dt_entrada = dt_entrada;
	}

	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDt_entrega() {
		return dt_entrega;
	}

	public void setDt_entrega(Date dt_entrega) {
		this.dt_entrega = dt_entrega;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	public Status_Pericia getStatus() {
		return status;
	}

	public void setStatus(Status_Pericia status) {
		this.status = status;
	}

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "cid_id", nullable = false)
	public Cid10 getCid() {
		return cid;
	}

	public void setCid(Cid10 cid) {
		this.cid = cid;
	}

	@Column(length = 10, nullable = false)
	public String getCo_procedimento() {
		return co_procedimento;
	}

	public void setCo_procedimento(String co_procedimento) {
		this.co_procedimento = co_procedimento;
	}

	@Column(length = 250, nullable = false)
	public String getNo_procedimento() {
		return no_procedimento;
	}

	public void setNo_procedimento(String no_procedimento) {
		this.no_procedimento = no_procedimento;
	}

	@ManyToOne()
	@JoinColumn(name = "estabelecimento_origem_id", nullable = false)
	public Fornecedor getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Fornecedor estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@ManyToOne()
	@JoinColumn(name = "estabelecimento_destino_id", nullable = false)
	public Fornecedor getEstabelecimentoDestino() {
		return estabelecimentoDestino;
	}

	public void setEstabelecimentoDestino(Fornecedor estabelecimentoDestino) {
		this.estabelecimentoDestino = estabelecimentoDestino;
	}

	@ManyToOne()
	@JoinColumn(name = "paciente_id", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@ManyToOne()
	@JoinColumn(name = "entrada_user_id", nullable = false)
	public Usuario getUsuario_entrada() {
		return usuario_entrada;
	}

	public void setUsuario_entrada(Usuario usuario_entrada) {
		this.usuario_entrada = usuario_entrada;
	}

	@ManyToOne()
	@JoinColumn(name = "saida_user_id")
	public Usuario getUsuario_saida() {
		return usuario_saida;
	}

	public void setUsuario_saida(Usuario usuario_saida) {
		this.usuario_saida = usuario_saida;
	}

	@ManyToOne()
	@JoinColumn(name = "medico_solicitante_id")
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Column(length = 200)
	public String getNomeRecebor() {
		return nomeRecebor;
	}

	public void setNomeRecebor(String nomeRecebor) {
		this.nomeRecebor = nomeRecebor;
	}

	@Column
	public String getRgCpf() {
		return rgCpf;
	}

	public void setRgCpf(String rgCpf) {
		this.rgCpf = rgCpf;
	}

	@Column(length = 1000)
	public String getObs_entrega() {
		return obs_entrega;
	}

	public void setObs_entrega(String obs_entrega) {
		this.obs_entrega = obs_entrega;
	}
	
	@Column(length = 1000)
	public String getObs_entrada() {
		return obs_entrada;
	}

	public void setObs_entrada(String obs_entrada) {
		this.obs_entrada = obs_entrada;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDoc")
	public StatusTipoDocumento getDoc() {
		return doc;
	}

	public void setDoc(StatusTipoDocumento doc) {
		this.doc = doc;
	}

	@Column
	public String getContatoCel() {
		return contatoCel;
	}

	public void setContatoCel(String contatoCel) {
		this.contatoCel = contatoCel;
	}

	@Column
	public String getContatoCel1() {
		return contatoCel1;
	}

	public void setContatoCel1(String contatoCel1) {
		this.contatoCel1 = contatoCel1;
	}
	
	
	@Transient
	public String getCpfFormatado(){
		if(this.getRgCpf()!= null && this.getRgCpf().length() == 11){
			return new CPFFormatter().format(getRgCpf());
		}
		return "";
	}

	@Transient
	public boolean isPacieneAssociado() {
		return this.paciente != null;
	}

	@Transient
	public boolean isEstabelecimentoAssociado() {
		return this.estabelecimento != null;
	}

	@Transient
	public boolean isEstabelecimentoDestinoAssociado() {
		return this.estabelecimentoDestino != null;
	}

	@Transient
	public boolean isNovo() {
		return getId_pericia() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	@PreUpdate
	@PrePersist
	public void toUpper() {
		if (getNomeRecebor() != null) {
			setNomeRecebor(getNomeRecebor().toUpperCase());
		}
		if (getObs_entrega() != null) {
			setObs_entrega(getObs_entrega().toUpperCase());
		}
		if (getObs_entrada() != null) {
			setObs_entrada(getObs_entrada().toUpperCase());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_pericia == null) ? 0 : id_pericia.hashCode());
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
		Pericia other = (Pericia) obj;
		if (id_pericia == null) {
			if (other.id_pericia != null)
				return false;
		} else if (!id_pericia.equals(other.id_pericia))
			return false;
		return true;
	}

	

}
