package com.ejsistemas.semsa.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.event.InternaAlteradoEvent;
import com.ejsistemas.semsa.model.Cid10;
import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.qualificador.edicao.InternaEdicao;
import com.ejsistemas.semsa.repository.CidRepository;
import com.ejsistemas.semsa.repository.ClinicaRepository;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.InternacaoRepository;
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.repository.MedicoRepository;
import com.ejsistemas.semsa.repository.PacienteRepository;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.repository.filter.InternacaoFilter;
import com.ejsistemas.semsa.repository.filter.LeitoFilter;
import com.ejsistemas.semsa.service.CadastroInternacaoService;
import com.ejsistemas.semsa.service.CadastroLeitoService;
import com.ejsistemas.semsa.service.CancelamentoInternacaoService;
import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.service.TransferenciaPacienteService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@ViewScoped
public class CadastroInternaBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	CancelamentoInternacaoService cancelamentoInternacaoService;
	@Inject
	CadastroInternacaoService cadastroInternacaoService;
	@Inject
	CadastroLeitoService cadastroLeitoService;
	@Inject
	FornecedorRepository fornecedorRepository;
	@Inject
	ClinicaRepository clinicaRepository;
	@Inject
	LeitoRepository leitoRepository;
	@Inject
	MedicoRepository medicoRepository;
	@Inject
	PacienteRepository pacienteRepository;
	@Inject
	InternacaoRepository internacaoRepository;
	@Inject
	CidRepository cidRepository;
	@Inject
	TransferenciaPacienteService transferenciaPacienteService;
	@Inject
	private Seguranca seguranca;

	private List<Fornecedor> fornecedors = new ArrayList<>();
	private List<Clinica> clinicas = new ArrayList<>();
	private List<Leito> leitos = new ArrayList<>();
	private List<Leito> leitosGeral = new ArrayList<>();
	private List<Medico> medicos = new ArrayList<>();
	private List<Paciente> pacientes = new ArrayList<>();
	private List<Internacao> internacoes = new ArrayList<>();

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	@Inject
	private Internacao transferencia;
	@Inject
	private Internacao transf;

	private Fornecedor hospital = null;
	private Clinica clinica;
	private LeitoFilter leitoFilter;
	private Leito leito;
	private Leito anterior;
	private Medico medicoSolicitanteAnterior;
	private Cid10 cidAnterior;
	private InternacaoFilter internacaoFilter;

	private boolean alta = false;
	private boolean cancela = false;
	private boolean transferivel = false;

	private String obs_internacao;

	@Produces
	@InternaEdicao
	private Internacao internacao;

	public CadastroInternaBean() {
		limpar();
	}

	public void inicializar() {
		// importarCID();
			
		this.fornecedors = fornecedorRepository.hospitalFiltrados(new FornecedorFilter());
		this.clinicas = clinicaRepository.filtrados(new ClinicaFilter());
		this.medicos = medicoRepository.buscarTodosMedicos();
		//this.pacientes = pacienteRepository.listarTodos();
		
		this.internacaoFilter = new InternacaoFilter();
		this.internacaoFilter.setStatus("OCUPADO");
		this.internacoes = internacaoRepository.filtradosOrderDesc(internacaoFilter);
	
	
			
	}

	public void salvar() {
		if (this.internacao.isNovo()) {
			if (!checkDuplicidadeInternacaoPaciente(internacao)) {
				if (checkLeitoDisponivel(internacao)) {
					this.internacao.setDataInternacao(LocalDateTime.now());
					this.internacao.setStatus("OCUPADO");
					this.internacao.setUsuario(seguranca.getUsuario());
					this.internacao.setCod_controle(this.internacao.getLeito().getHospital().getId() + "."
							+ this.internacao.getLeito().getClinica().getId() + "."
							+ this.getInternacao().getLeito().getId());
					
					if(this.internacao.getPaciente().getDt_nascimento() == null
							|| this.internacao.getPaciente().getDt_nascimento().equals("")){
						
						FacesUtil.addErrorMessage("Paciente sem data de nascimento, por favor atualize-o");
					}
					else{

						this.internacao = cadastroInternacaoService.salvar(internacao);
						this.leito.setStatus("OCUPADO");
						this.leito = cadastroLeitoService.salvar(leito);
						FacesUtil.addInfoMessage("Paciente: " + this.internacao.getPaciente().getNome());
						this.internacoes = internacaoRepository.filtradosOrderDesc(internacaoFilter);
						FacesUtil.addInfoMessage("Internado(a) com sucesso!");
						limpar();
						hospital = null;
						
					}
					

				} else {
					FacesUtil.addAlerMessage("Leito não disponivel, por favor verifique com a direção de leitos!");
				}
			} else {
				FacesUtil.addAlerMessage("Paciente já encontra-se, internado!");
			}

		} else {
			FacesUtil.addAlerMessage("Por favor, click no botão novo para realizar uma nova internação!");
		}
	}

	private boolean checkDuplicidadeInternacaoPaciente(Internacao internacao2) {
		boolean existeDuplicidade = false;
		internacaoFilter = new InternacaoFilter();
		internacaoFilter.setPaciente(internacao.getPaciente());
		internacaoFilter.setStatus("OCUPADO");
		List<Internacao> list = internacaoRepository.filtradoOcupados(internacaoFilter);
		for (Internacao internado : list) {
			if (internado.isOcupado()) {
				existeDuplicidade = true;
				break;
			}
		}

		return existeDuplicidade;
	}
	
	public List<Fornecedor> pesquisaFornecedores(String campos){
		List<Fornecedor> fornecedores = fornecedorRepository.porFornecedorFantasiaHospital(campos);
		if(medicos.size() == 0){
			FacesUtil.addAlerMessage("Nenhum hospital encontrado");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		
		return fornecedores;
		
	}

	private boolean checkLeitoDisponivel(Internacao internacao2) {
		boolean existeLeitoDiponivel = false;
		this.leito = this.leitoRepository.porId(this.internacao.getLeito().getId());
		if (this.leito.getStatus().equals("AUSENTE")) {
			existeLeitoDiponivel = true;
		}
		return existeLeitoDiponivel;
	}

	public void buscarHospital(ValueChangeEvent h) {
		this.hospital = (Fornecedor) h.getNewValue();
	}
	
	public void buscarLeitosDisponiveis(ValueChangeEvent e) {
		clinica = (Clinica) e.getNewValue();
		
		if ((!hospital.isNovo()) && (!clinica.isNovo())) {
			leitoFilter = new LeitoFilter();
			this.leitoFilter.setFornecedor(hospital);
			this.leitoFilter.setClinica(clinica);
			this.leitoFilter.setStatus("AUSENTE");
			leitos = leitoRepository.filtrados(leitoFilter);
			this.leitoFilter = new LeitoFilter();
		} else {
			FacesUtil.addAlerMessage("Por favor, selecione respectivamente Hospital e Clínica!");
		}

	}

	public void internacaoAlterado(@Observes InternaAlteradoEvent event) {
		this.internacao = event.getInternacao();
	}

	public void limpar() {
		internacao = new Internacao();
		//hospital = new Fornecedor();
		clinica = new Clinica();
		leito = new Leito();
		anterior = new Leito();
		leitos = new ArrayList<>();
	}

	public void checkInternacao() {
		cancela = false;
		alta = false;
		setHospital(null);
		setClinica(null);
		// importarCID();

		if (!internacao.isNovo()) {
			transferivel = true;
			leitos = new ArrayList<>();
			medicoSolicitanteAnterior = this.internacao.getMedicoSolicitante();
			cidAnterior = this.internacao.getCid();
			
			this.internacao.setMedicoSolicitante(null);
			this.internacao.setCid(null);
			anterior = this.internacao.getLeito();
			obs_internacao = internacao.getObs_interncao();
			internacao.setObs_interncao(null);
			if (internacao.getInternacao() != null) {
				transferencia.setInternacao(internacao.getInternacao());
				transferencia.setDataInternacao(LocalDateTime.now());
				transferencia.setStatus("OCUPADO");

			} else {
				transf.setInternacao(internacao);
				transf.setDataInternacao(LocalDateTime.now());
				transf.setStatus("OCUPADO");
			}

		}
	}

	public String transferenciaPaciente() {
		if (internacao.isOcupado()) {
			if (chckeLeitoDisponivel(internacao)) {
				if (transferencia.getStatus() != null) {

					transferencia.setLeito(leito);
					transferencia.setPaciente(internacao.getPaciente());
					transferencia.setMedicoSolicitante(internacao.getMedicoSolicitante());
					transferencia.setObs_interncao(internacao.getObs_interncao());
					transferencia.setUsuario(seguranca.getUsuario());
					transferencia.setCid(internacao.getCid());
					
					this.transferencia.setCod_controle(this.internacao.getLeito().getHospital().getId() + "."
							+ this.internacao.getLeito().getClinica().getId() + "."
							+ this.getInternacao().getLeito().getId());

					transferencia = cadastroInternacaoService.salvar(transferencia);

					this.leito.setStatus("OCUPADO");
					cadastroLeitoService.salvar(leito);

					internacao.setLeito(anterior);
					internacao.setDataAlta(transferencia.getDataInternacao());
					internacao.setMedicoSolicitante(medicoSolicitanteAnterior);
					internacao.setCid(cidAnterior);
					internacao.setObs_interncao(obs_internacao);
					internacao.setStatus("TRANSFERIDO");

					internacao = this.transferenciaPacienteService.transferencia(internacao);

					this.getAnterior().setStatus("AUSENTE");
					cadastroLeitoService.salvar(anterior);

					FacesUtil.addInfoMessage("Transferência de paciente: " + this.internacao.getPaciente().getNome()
							+ ", com código de internação: " + this.internacao.getId());
					FacesUtil.addInfoMessage("Realizado com sucesso!");
					this.internacao = new Internacao();

					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

					return "Internacao.xhtml?faces-redirect=true";
				} else {

					transf.setLeito(leito);
					transf.setMedicoSolicitante(internacao.getMedicoSolicitante());
					transf.setPaciente(internacao.getPaciente());
					transf.setObs_interncao(internacao.getObs_interncao());
					transf.setCid(internacao.getCid());
					transf.setUsuario(seguranca.getUsuario());
					this.transf.setCod_controle(this.internacao.getLeito().getHospital().getId() + "."
							+ this.internacao.getLeito().getClinica().getId() + "."
							+ this.getInternacao().getLeito().getId());

					transf = this.cadastroInternacaoService.salvar(transf);

					this.getAnterior().setStatus("AUSENTE");
					cadastroLeitoService.salvar(anterior);

					internacao.setDataAlta(this.transf.getDataInternacao());
					internacao.setMedicoSolicitante(medicoSolicitanteAnterior);
					internacao.setLeito(anterior);
					internacao.setCid(cidAnterior);
					internacao.setObs_interncao(obs_internacao);
					internacao.setStatus("TRANSFERIDO");
					internacao = this.transferenciaPacienteService.transferencia(internacao);

					this.leito.setStatus("OCUPADO");
					cadastroLeitoService.salvar(leito);

					FacesUtil.addInfoMessage("Transferência de paciente: " + this.internacao.getPaciente().getNome());
					FacesUtil.addInfoMessage("Realizado com sucesso!");
					this.internacao = new Internacao();

					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

					return "Internacao.xhtml?faces-redirect=true";

				}

			} else {
				FacesUtil.addAlerMessage("Leito não disponível, por favor, verifique com a Administração!");
			}

		} else {
			throw new NegocioException("Paciente não pode ser transferido com status: " + internacao.getStatus() + ".");
		}

		return null;

	}

	private boolean chckeLeitoDisponivel(Internacao internacao2) {
		boolean existeLeitoDisponivel = false;
		this.leito = this.leitoRepository.porId(internacao2.getLeito().getId());
		if (this.leito.getStatus().equals("AUSENTE")) {
			existeLeitoDisponivel = true;
		}
		return existeLeitoDisponivel;
	}

	public Internacao getInternacao() {
		return internacao;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
		if (internacao.isExistente()) {
		}

	}

	public void acaoAlta() {
		if (internacao.isOcupado()) {
			alta = true;
			cancela = false;
			transferivel = false;
			buscarLeitosAcao();

		}
	}

	public void acaoCancelar() {
		if (internacao.isOcupado()) {
			cancela = true;
			alta = false;
			transferivel = false;
			buscarLeitosAcao();

		}
	}

	public void buscarLeitosAcao() {

		setHospital(this.internacao.getLeito().getHospital());
		setClinica(this.internacao.getLeito().getClinica());
		leitoFilter = new LeitoFilter();
		this.leitoFilter.setFornecedor(hospital);
		this.leitoFilter.setClinica(clinica);
		// this.leitoFilter.setStatus("AUSENTE");
		leitos = leitoRepository.filtrados(leitoFilter);
		this.leitoFilter = new LeitoFilter();
	}
	
	
	public List<Paciente> pesquisaPaciente(String campos) {
		List<Paciente> pacientes = pacienteRepository.porNomeCNS(campos);
		if (pacientes.size() == 0) {
			FacesUtil.addAlerMessage("Nenhum paciente encontrado!");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("tabPanelWidget:msg");
		}
		return pacientes;

	}

	public List<Cid10> pesquisaCid(String str) {
		List<Cid10> cid = cidRepository.porNomes(str);
		if (cid.size() == 0) {
			FacesUtil.addAlerMessage("Cid não encontrado!");
		}
		return cid;
	}

	public void imprimirRecibo(Long codigo) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		// String image = "/resources/images/logo.jpg";

		parametros.put("chave_internacao", codigo);
		parametros.put("logo",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/imagem3.png"));
		parametros.put("logo1",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/Imagem2.png"));
		// parametros.put("idade", paciente.getCalculaIdade());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Internacao.jasper", "Resgistro Internacao.pdf",
				this.response, parametros);

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}

	}

	
	public String visualizarDetalhe(){
		return "DetalheRequisicoes.xhtml?faces-redirect=true";
	}
	
	@SuppressWarnings("resource")
	public void importarCID() {

		File csv = new File("C:\\Users\\Edenilson\\Downloads\\CID10CSV\\CID-10-SUBCATEGORIAS.CSV");
		SQLQuery query;
		List<String> stringList;
		Session session = manager.unwrap(Session.class);

		try {
			String linhadoArquivo = new String();

			Scanner leitor = new Scanner(csv);
			leitor.nextLine();
			System.out.println(leitor.hasNextInt());

			while (leitor.hasNextLine()) {
				linhadoArquivo = leitor.nextLine();
				String[] valoresentreVirgula = linhadoArquivo.split(";");

				String sql = "INSERT INTO tb_cid(subcat, classif, restrsexo, causaobito, descricao, descrabrev, refer, excluidos) "
						+ "VALUES(:subcat, :classif, :restrsexo, :causaobito, :descricao, :descrabrev, :refer, :excluidos)";
				query = session.createSQLQuery(sql);
				int i = 0;

				if (valoresentreVirgula.length == 6) {
					for (int j = 0; j < 6; j++) {
						String string = valoresentreVirgula[j];
						if (j == 0) {
							query.setParameter("refer", null);
							query.setParameter("excluidos", null);
						}

						if (j == 0) {
							if (string.isEmpty()) {
								query.setParameter("subcat", null);
							} else {
								query.setParameter("subcat", string);
							}
						}
						if (j == 1) {
							if (string.isEmpty()) {
								query.setParameter("classif", null);
							} else {
								query.setParameter("classif", string);
							}
						}
						if (j == 2) {
							if (string.isEmpty()) {
								query.setParameter("restrsexo", null);
							} else {
								query.setParameter("restrsexo", string);
							}

						}
						if (j == 3) {
							if (string.isEmpty()) {
								query.setParameter("causaobito", null);
							} else {
								query.setParameter("causaobito", string);
							}
						}
						if (j == 4) {
							if (string.isEmpty()) {
								query.setParameter("descricao", null);
							} else {
								query.setParameter("descricao", string);
							}
						}
						if (j == 5) {
							if (string.isEmpty()) {
								query.setParameter("descrabrev", null);
							} else {
								query.setParameter("descrabrev", string);
							}
						}
						if (j == 6) {
							if (string.isEmpty()) {
								query.setParameter("refer", null);
							} else {
								query.setParameter("refer", string);
							}
						}
						if (j == 7) {
							if (string.isEmpty()) {
								query.setParameter("excluidos", null);
							} else {
								query.setParameter("excluidos", string);
							}
						}
						i = i + 1;

					}
					query.executeUpdate();

				}

				if (valoresentreVirgula.length == 7) {

					for (int j = 0; j < 7; j++) {
						String string = valoresentreVirgula[j];

						if (j == 0) {
							query.setParameter("excluidos", null);
						}

						if (j == 0) {
							if (string.isEmpty()) {
								query.setParameter("subcat", null);
							} else {
								query.setParameter("subcat", string);
							}
						}
						if (j == 1) {
							if (string.isEmpty()) {
								query.setParameter("classif", null);
							} else {
								query.setParameter("classif", string);
							}
						}
						if (j == 2) {
							if (string.isEmpty()) {
								query.setParameter("restrsexo", null);
							} else {
								query.setParameter("restrsexo", string);
							}

						}
						if (j == 3) {
							if (string.isEmpty()) {
								query.setParameter("causaobito", null);
							} else {
								query.setParameter("causaobito", string);
							}
						}
						if (j == 4) {
							if (string.isEmpty()) {
								query.setParameter("descricao", null);
							} else {
								query.setParameter("descricao", string);
							}
						}
						if (j == 5) {
							if (string.isEmpty()) {
								query.setParameter("descrabrev", null);
							} else {
								query.setParameter("descrabrev", string);
							}
						}
						if (j == 6) {
							if (string.isEmpty()) {
								query.setParameter("refer", null);
							} else {
								query.setParameter("refer", string);
							}
						}
						if (j == 7) {
							if (string.isEmpty()) {
								query.setParameter("excluidos", null);
							} else {
								query.setParameter("excluidos", string);
							}
						}
						i = i + 1;
					}
					query.executeUpdate();
				}

				if (valoresentreVirgula.length == 8) {

					for (int j = 0; j < 8; j++) {
						String string = valoresentreVirgula[j];

						if (j == 0) {
							if (string.isEmpty()) {
								query.setParameter("subcat", null);
							} else {
								query.setParameter("subcat", string);
							}
						}
						if (j == 1) {
							if (string.isEmpty()) {
								query.setParameter("classif", null);
							} else {
								query.setParameter("classif", string);
							}
						}
						if (j == 2) {
							if (string.isEmpty()) {
								query.setParameter("restrsexo", null);
							} else {
								query.setParameter("restrsexo", string);
							}

						}
						if (j == 3) {
							if (string.isEmpty()) {
								query.setParameter("causaobito", null);
							} else {
								query.setParameter("causaobito", string);
							}
						}
						if (j == 4) {
							if (string.isEmpty()) {
								query.setParameter("descricao", null);
							} else {
								query.setParameter("descricao", string);
							}
						}
						if (j == 5) {
							if (string.isEmpty()) {
								query.setParameter("descrabrev", null);
							} else {
								query.setParameter("descrabrev", string);
							}
						}
						if (j == 6) {
							if (string.isEmpty()) {
								query.setParameter("refer", null);
							} else {
								query.setParameter("refer", string);
							}
						}
						if (j == 7) {
							if (string.isEmpty()) {
								query.setParameter("excluidos", null);
							} else {
								query.setParameter("excluidos", string);
							}
						}
						i = i + 1;
					}
					query.executeUpdate();
				}

			}

		} catch (FileNotFoundException e) {
			e.getMessage();
		}

	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedors;
	}

	public List<Leito> getLeitos() {
		return leitos;
	}

	public Fornecedor getHospital() {
		return hospital;
	}

	public void setHospital(Fornecedor hospital) {
		this.hospital = hospital;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public LeitoFilter getLeitoFilter() {
		return leitoFilter;
	}

	public void setLeitoFilter(LeitoFilter leitoFilter) {
		this.leitoFilter = leitoFilter;
	}

	public InternacaoFilter getInternacaoFilter() {
		return internacaoFilter;
	}

	public void setInternacaoFilter(InternacaoFilter internacaoFilter) {
		this.internacaoFilter = internacaoFilter;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public List<Leito> getLeitosGeral() {
		return leitosGeral;
	}

	public Leito getLeito() {
		return leito;
	}

	public Leito getAnterior() {
		return anterior;
	}

	public void setAnterior(Leito anterior) {
		this.anterior = anterior;
	}

	public List<Internacao> getInternacoes() {
		return internacoes;
	}

	public void setTransferencia(Internacao transferencia) {
		this.transferencia = transferencia;
	}

	public Internacao getTransferencia() {
		return transferencia;
	}

	public Medico getMedicoSolicitanteAnterior() {
		return medicoSolicitanteAnterior;
	}

	public void setMedicoSolicitanteAnterior(Medico medicoSolicitanteAnterior) {
		this.medicoSolicitanteAnterior = medicoSolicitanteAnterior;
	}

	public Cid10 getCidAnterior() {
		return cidAnterior;
	}

	public void setCidAnterior(Cid10 cidAnterior) {
		this.cidAnterior = cidAnterior;
	}

	public boolean isCancelavel() {
		return cancela;
	}

	public boolean isTransferivel() {
		return transferivel;
	}

	public boolean isAlta() {
		return alta;
	}
	
	public boolean isCancela(){
		return cancela;
	}
}
