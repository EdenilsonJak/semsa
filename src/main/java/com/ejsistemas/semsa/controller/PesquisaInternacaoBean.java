package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.repository.ClinicaRepository;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.InternacaoRepository;
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.repository.PacienteRepository;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.repository.filter.InternacaoFilter;
import com.ejsistemas.semsa.repository.filter.LeitoFilter;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class PesquisaInternacaoBean implements Serializable {

	/**
	 * Desenvolvido por Edenilson Mendonça Classe Bean responsável por pesquisa
	 * de Internações
	 */

	private static final long serialVersionUID = 1L;

	@Inject
	InternacaoRepository internacaoRepository;
	@Inject
	FornecedorRepository fornecedorRepository;
	@Inject
	ClinicaRepository clinicaRepository;
	@Inject
	LeitoRepository leitoRepository;
	@Inject
	PacienteRepository pacienteRepository;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private List<Fornecedor> fornecedors = new ArrayList<Fornecedor>();
	private List<Clinica> clinicas;
	private List<Leito> leitos;
	private List<Paciente> pacientes;
	private List<Internacao> internacoes;
	private List<Internacao> transferencias;

	private InternacaoFilter internacaoFilter;

	private Internacao internacao;

	private boolean existeHistorico = false;

	public PesquisaInternacaoBean() {
		internacaoFilter = new InternacaoFilter();
		internacoes = new ArrayList<>();
		transferencias = new ArrayList<>();
		clinicas = new ArrayList<>();
		leitos = new ArrayList<>();
		pacientes = new ArrayList<>();
	}

	public void inicializar() {
		fornecedors = fornecedorRepository.hospitalFiltrados(new FornecedorFilter());
		clinicas = clinicaRepository.filtrados(new ClinicaFilter());
		// leitos = leitoRepository.filtrados(new LeitoFilter());
		// pacientes = pacienteRepository.listarTodos();
	}

	public List<Paciente> completarPaciente(String valor) {
		List<Paciente> pacientes = pacienteRepository.porValores(valor);
		if (pacientes.size() == 0) {
			FacesUtil.addAlerMessage("Cliente não encontrado!");
		}
		return pacientes;

	}

	public void listarLeitosPorClinica() {
		leitos = new ArrayList<>();
		if (internacaoFilter.getClinica() != null) {
			LeitoFilter filter = new LeitoFilter();
			filter.setClinica(internacaoFilter.getClinica());
			leitos = leitoRepository.filtrados(filter);
		}

	}

	public void pesquisar() {
		existeHistorico = false;
		internacoes = new ArrayList<>();
		internacoes = internacaoRepository.internacaoPrincipal(internacaoFilter);
		existeHistorico = false;
		internacaoFilter = new InternacaoFilter();
		transferencias = new ArrayList<>();

	}

	public void onSelect() {
		existeHistorico = false;
		if (internacao != null) {
			InternacaoFilter internaFilter = new InternacaoFilter();
			internaFilter.setInternacao(internacao);
			transferencias = internacaoRepository.internacaoHistorico(internaFilter);
			if (transferencias.size() != 0) {
				existeHistorico = true;
			}
		}
	}

	public InternacaoFilter getInternacaoFilter() {
		return internacaoFilter;
	}

	public void setInternacaoFilter(InternacaoFilter internacaoFilter) {
		this.internacaoFilter = internacaoFilter;
	}

	public List<Internacao> getInternacoes() {
		return internacoes;
	}

	public List<Internacao> getTransferencias() {
		return transferencias;
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedors;
	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public List<Leito> getLeitos() {
		return leitos;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public Internacao getInternacao() {
		return internacao;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public boolean isExisteHistorico() {
		return existeHistorico;
	}

	public void producoes() {

		Map<String, Object> parametros = new HashMap<String, Object>();
		// String image = "/resources/images/logo.jpg";

		parametros.put("codigo", this.internacaoFilter.getHospital().getId());
		parametros.put("dataInicio", this.internacaoFilter.getInicio());
		parametros.put("dataFim", this.internacaoFilter.getFim());
		parametros.put("logo",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/imagem3.png"));
		parametros.put("logo1",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/Imagem2.png"));
		// parametros.put("idade", paciente.getCalculaIdade());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Producoes.jasper", "Producoes Internacao.pdf",
				this.response, parametros);

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}

	}
}
