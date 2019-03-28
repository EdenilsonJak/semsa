package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.model.Status_Movimento_Requisicao;
import com.ejsistemas.semsa.model.UnidadeSaude;
import com.ejsistemas.semsa.repository.ItemReceituarioRepository;
import com.ejsistemas.semsa.repository.PacienteRepository;
import com.ejsistemas.semsa.repository.UnidadeSaudeRepository;
import com.ejsistemas.semsa.repository.filter.RequisicaoRecepcaoFilter;
import com.ejsistemas.semsa.service.CadastroItemReceituarioService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@ViewScoped
public class PesquisaRequisicoesRecepcao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemReceituarioRepository itemReceituarioRepository;
	@Inject
	private PacienteRepository pacienteRepository;
	@Inject
	private UnidadeSaudeRepository unidadeSaudeRepositoy;
	@Inject
	private CadastroItemReceituarioService cadastroItemReceituarioService;
	@Inject
	private ItemReceituario itemReceituario;
	@Inject
	private Seguranca usuario;
	@Inject
	private RequisicaoRecepcaoFilter requisicaoRecepcaoFilter;
	@Inject
	private FacesContext facesContext;
	@Inject
	private EntityManager manager;
	@Inject
	private HttpServletResponse response;
	
	private LazyDataModel<ItemReceituario> lazy = new LazyDataModel<ItemReceituario>() {
	
	
	};
	
	
	private boolean Marcar = false;
	private boolean Cancelar = false;

	private List<ItemReceituario> listaGeral = new ArrayList<>();
	private List<ItemReceituario> listaFilter = new ArrayList<>();
	private List<UnidadeSaude> listaUnidades = new ArrayList<>();
 
	public void inicializar() {
		listaEspera();
		//this.listaUnidades = unidadeSaudeRepositoy.todosUnidadedeSaude();
		//requisicaoRecepcaoFilter = new RequisicaoRecepcaoFilter();
		// limpar();
	}
	
	public void inicializarConsultaRequisicoes() {
	//	listaEspera();
		this.listaUnidades = unidadeSaudeRepositoy.todosUnidadedeSaude();
		//requisicaoRecepcaoFilter = new RequisicaoRecepcaoFilter();
		// limpar();
	}

	/*
	 * public void limpar(){ this.itemRequisicao = new ItemReceituario(); }
	 */
	
	
	public void limpar(CloseEvent event){
		if(itemReceituario.isExistente()){
			itemReceituario = new ItemReceituario();
		}
	}
	
	public void pesquisar(){
		if(requisicaoRecepcaoFilter.getDataFimRequisicao() != null){
			requisicaoRecepcaoFilter.setDataFimRequisicao(new Date(requisicaoRecepcaoFilter.getDataFimRequisicao().getTime() + 24 * 60 * 60 * 1000));
		}
		if(requisicaoRecepcaoFilter.getDataFimRequisicaoMarcacao() != null){
			requisicaoRecepcaoFilter.setDataFimRequisicaoMarcacao(new Date(requisicaoRecepcaoFilter.getDataFimRequisicaoMarcacao().getTime() + 24 * 60 * 60 * 1000));
		}
		listaFilter = itemReceituarioRepository.consultaFilter(requisicaoRecepcaoFilter);
	}

	public List<Paciente> pesquisaPaciente(String campos){
		List<Paciente> pacientes = pacienteRepository.porNomeCNS(campos);		
		if(pacientes.size() == 0){
			FacesUtil.addAlerMessage("Nenhum paciente encontrado!");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		return pacientes;
	}

	public void selecionaMarcar() {
		this.Marcar = true;
	}

	public void selecionaCancelar() {
		this.Cancelar = true;
	}

	public void listaEspera() {
		listaGeral = itemReceituarioRepository.montaLista();
	}

	public void salvarMarcacao() {
		itemReceituario.setUsuarioMarcacao(usuario.getUsuario());
		cadastroItemReceituarioService.salvarMarcacaoItemReceituario(itemReceituario);
		listaEspera();
	}

	public void salvarCancelamento() {
		/*RequestContext context = RequestContext.getCurrentInstance();
		ArrayList<String> mylist = new ArrayList<String>(){
			{
				add("dataItem");
				
			}
		};*/
		itemReceituario.setUsuarioCancela(usuario.getUsuario());
		cadastroItemReceituarioService.salvarCancelamentoItemReceituario(itemReceituario);

	}

	public List<ItemReceituario> getListaGeral() {
		return listaGeral;
	}

	public ItemReceituarioRepository getItemReceituarioRepository() {
		return itemReceituarioRepository;
	}

	public void setItemRequisicao(ItemReceituario itemReceituario) {
		this.itemReceituario = itemReceituario;
		if (Marcar == true) {
			this.Marcar = false;
		}
		if (Cancelar == true) {
			this.Cancelar = false;
		}
	}

	public ItemReceituario getItemReceituario() {
		return itemReceituario;
	}

	public boolean isMarcar() {
		return Marcar;
	}

	public boolean isCancelar() {
		return Cancelar;
	}

	public RequisicaoRecepcaoFilter getRequisicaoRecepcaoFilter() {
		return requisicaoRecepcaoFilter;
	}

	public void setRequisicaoRecepcaoFilter(RequisicaoRecepcaoFilter requisicaoRecepcaoFilter) {
		this.requisicaoRecepcaoFilter = requisicaoRecepcaoFilter;
	}

	public List<UnidadeSaude> getListaUnidades() {
		return listaUnidades;
	}
	
	public List<ItemReceituario> getListaFilter() {
		return listaFilter;
	}
	
	public void relatorio() {

		Map<String, Object> parametros = new HashMap<String, Object>();
		// String image = "/resources/images/logo.jpg";

		//parametros.put("prioridade", this.requisicaoRecepcaoFilter.getStatus());
		parametros.put("dataInicio", this.requisicaoRecepcaoFilter.getDataInicioRequisicao());
		parametros.put("dataFim", this.requisicaoRecepcaoFilter.getDataFimRequisicao());
		parametros.put("logo",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/imagem3.png"));
		parametros.put("logo1",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/Imagem2.png"));
		// parametros.put("idade", paciente.getCalculaIdade());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Rel_Espera_Agrupamento_Solicitacao.jasper", "Relatório Operacional Requisições.pdf",
				this.response, parametros);

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}

	}
	
	public void imprimirSolicitacao() {
		this.requisicaoRecepcaoFilter.setStatus_movimento(Status_Movimento_Requisicao.ESPERA);

		Map<String, Object> parametros = new HashMap<String, Object>();
		// String image = "/resources/images/logo.jpg";

		//parametros.put("prioridade", this.requisicaoRecepcaoFilter.getStatus());
		parametros.put("dataInicio", this.requisicaoRecepcaoFilter.getDataInicioRequisicao());
		parametros.put("dataFim", this.requisicaoRecepcaoFilter.getDataFimRequisicao());
		parametros.put("movimentoStatus", this.requisicaoRecepcaoFilter.getStatus_movimento());
		parametros.put("cod_solicitacao", this.requisicaoRecepcaoFilter.getSolicitacao().getId());
		parametros.put("logo",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/imagem3.png"));
		parametros.put("logo1",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/Imagem2.png"));
		// parametros.put("idade", paciente.getCalculaIdade());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Rel_Espera_TipoSolicitacao_Periodo.jasper", "Relatório Operacional Requisições.pdf",
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
