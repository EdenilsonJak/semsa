package com.ejsistemas.semsa.controller.recepcao;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;

import com.ejsistemas.semsa.event.ReceituarioAlteradoEvent;
import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.model.Receituario;
import com.ejsistemas.semsa.model.Solicitacao;
import com.ejsistemas.semsa.model.Status_Prioridade_Requisicao;
import com.ejsistemas.semsa.model.UnidadeSaude;
import com.ejsistemas.semsa.repository.ItemReceituarioRepository;
import com.ejsistemas.semsa.repository.MedicoRepository;
import com.ejsistemas.semsa.repository.PacienteRepository;
import com.ejsistemas.semsa.repository.SolicitaoRepository;
import com.ejsistemas.semsa.repository.UnidadeSaudeRepository;
import com.ejsistemas.semsa.service.CadastroReceituarioService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.security.Seguranca;

@Named
@ViewScoped
public class CadastroRequisicoesRecepcao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroReceituarioService cadastroReceituarioService;
	@Inject
	private PacienteRepository pacienteRepository;
	@Inject
	private MedicoRepository MedicoRepository;
	@Inject
	private SolicitaoRepository solicitacaoRepository;
	@Inject
	private UnidadeSaudeRepository unidadeSaudeRepository;
	@Inject
	private ItemReceituarioRepository ItemReceituarioRepository;

	@Inject
	private EntityManager manager;

	@Produces
	@ReceituarioEdicao
	private Receituario receituario;
	@Inject
	private Seguranca usuario;

	@Inject
	private ItemReceituario itensRequisicoes;

	private UnidadeSaude unidadeSaudeEditavel;

	// private ItensRequisicoes itensRequisicoes
	private List<Paciente> pacientes;
	private List<Solicitacao> solicitacoes;
	private List<Solicitacao> solicitacaoRaizes;
	private Solicitacao solicitaoRaiz;
	//private List<UnidadeSaude> unidadeSaudes;
	private List<ItemReceituario> geral;

	private boolean flagRadioButton = false;
	
	private String filename;

	public CadastroRequisicoesRecepcao() {
		limpar();
	}

	public void inicializar() {
		this.solicitacaoRaizes = solicitacaoRepository.raizes();
		//this.medicos = MedicoRepository.buscarTodosMedicos();
		// this.pacientes = pacienteRepository.listarTodos();
		//this.unidadeSaudes = unidadeSaudeRepository.todosUnidadedeSaude();
		// this.receituario.setData(LocalDateTime.now());
		// this.receituario.adicionarRequisicaoVazio();
		geral = ItemReceituarioRepository.montaLista();

	}
	

	public void inicializarRecepcaoRequisicao() {
		this.solicitacaoRaizes = solicitacaoRepository.raizes();

	}

	public void salvar() {
		try {
			this.receituario.setData(new Date());
			this.receituario.setUsuarioEntrada(usuario.getUsuario());
			if(receituario.getPaciente().getDt_nascimento() == null
					|| receituario.getPaciente().getDt_nascimento().equals("")){
				FacesUtil.addErrorMessage("Data de nascimento é obrigatório, por favor atualize-o");
			}
			else{
				this.receituario = cadastroReceituarioService.salvar(receituario);
				FacesUtil.addInfoMessage("Registro gravado com sucesso!");
			}			

		} finally {
			/* this.receituario = new Receituario(); */
		}

		this.receituario = new Receituario();
		this.itensRequisicoes = new ItemReceituario();
		flagRadioButton = false;
	}

	@SuppressWarnings("serial")
	public void atualizarComponente() {
		this.itensRequisicoes = new ItemReceituario();
		this.receituario.setItens(new ArrayList<>());

		flagRadioButton = false;
		ArrayList<String> mylist = new ArrayList<String>() {
			{
				add("panel");
				add("prioridade");
				add("msg");
				add("dataItem");

			}
		};
		if (receituario.isPacienteAssociado()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("grid_panel_judicial");
			/* if(receituario.getPaciente().getSexo().equals("MASCULINO")){ */
			if (maiorIdade(receituario.getPaciente())) {
				this.itensRequisicoes.setPrioridade_status(Status_Prioridade_Requisicao.IDOSO);
				RequestContext contextMaiorIdade = RequestContext.getCurrentInstance();
				FacesUtil.addAlerMessage("Conforme, estatuto do Idoso Lei Nº 10.741, de Outubro de 2003.");
				FacesUtil.addAlerMessage("Paciente automaticamente ficará na PRIORIDADE de seu Direito.");
				contextMaiorIdade.update(mylist);
			} else {
				this.itensRequisicoes.setPrioridade_status(Status_Prioridade_Requisicao.NORMAL);
				RequestContext contextMaiorIdade = RequestContext.getCurrentInstance();
				contextMaiorIdade.update(mylist);
			}
			/*
			 * } else
			 * if(receituario.getPaciente().getSexo().equals("FEMININO")){
			 * if(maiorIdadeMulher(receituario.getPaciente())){
			 * this.itensRequisicoes.setPrioridade_status(
			 * Status_Prioridade_Requisicao.IDOSO); RequestContext
			 * contextMaiorIdade = RequestContext.getCurrentInstance();
			 * contextMaiorIdade.update(mylist); } else{
			 * this.itensRequisicoes.setPrioridade_status(
			 * Status_Prioridade_Requisicao.NORMAL); RequestContext
			 * contextMaiorIdade = RequestContext.getCurrentInstance();
			 * contextMaiorIdade.update(mylist); } }
			 */
		}

	}

	@SuppressWarnings("serial")
	public void adicionarItemRequisicao() {
		ArrayList<String> mylist = new ArrayList<String>() {
			{
				add("panel");
				add("prioridade");
			}
		};
		/*if (itensRequisicoes.getDataRequisicao().after(getHojeMais5())
				|| itensRequisicoes.getDataRequisicao().before(getUmAntes())) {
			FacesUtil.addErrorMessage("Data requisição minima ou máxima não permitida no sistema.");
		} */
			//else {

			itensRequisicoes.setReceituario(receituario);
			receituario.getItens().add(itensRequisicoes);
			// this.receituarioAlteradoEvent.fire(new
			// ReceituarioAlteradoEvent(this.receituario));
			this.itensRequisicoes = new ItemReceituario();
			if (maiorIdade(receituario.getPaciente())) {
				this.itensRequisicoes.setPrioridade_status(Status_Prioridade_Requisicao.IDOSO);
				RequestContext contextMaiorIdade = RequestContext.getCurrentInstance();
				contextMaiorIdade.update(mylist);
			} else {
				this.itensRequisicoes.setPrioridade_status(Status_Prioridade_Requisicao.NORMAL);
				RequestContext contextMaiorIdade = RequestContext.getCurrentInstance();
				contextMaiorIdade.update(mylist);
			}
		//}

		/*
		 * else if(receituario.getPaciente().getSexo().equals("FEMININO")){
		 * if(maiorIdadeMulher(receituario.getPaciente())){
		 * this.itensRequisicoes.setPrioridade_status(
		 * Status_Prioridade_Requisicao.IDOSO); RequestContext contextMaiorIdade
		 * = RequestContext.getCurrentInstance();
		 * contextMaiorIdade.update(mylist); } else{
		 * this.itensRequisicoes.setPrioridade_status(
		 * Status_Prioridade_Requisicao.NORMAL); RequestContext
		 * contextMaiorIdade = RequestContext.getCurrentInstance();
		 * contextMaiorIdade.update(mylist); }
		 */

	}

	public void prioridadeSelect() {
		ArrayList<String> mylist = new ArrayList<String>() {
			{
				add("panel");
				add("prioridade");
			}
		};
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(mylist);
	}

	public List<Paciente> pesquisaPaciente(String campos) {
		List<Paciente> pacientes = pacienteRepository.porNomeCNS(campos);
		if (pacientes.size() == 0) {
			FacesUtil.addAlerMessage("Nenhum paciente encontrado!");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		return pacientes;

	}
	
	public List<Medico> pesquisaMedicos(String campos){
		List<Medico> medicos = MedicoRepository.porValores(campos);
		if(medicos.size() == 0){
			FacesUtil.addAlerMessage("Nenhum médico encontrado");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		
		return medicos;
		
	}
	
	public List<UnidadeSaude> pesquisarUnidades(String campos){
		List<UnidadeSaude> unidades = unidadeSaudeRepository.porValores(campos);
		if(unidades.size() == 0){
			FacesUtil.addAlerMessage("Nenhum estabelecimento de saúde encontrado");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		
		return unidades;
	}
	

	public boolean maiorIdade(Paciente paciente) {
		GregorianCalendar dataHj = new GregorianCalendar();
		GregorianCalendar dataNascimento = new GregorianCalendar();
		if (paciente.getDt_nascimento() != null) {
			dataNascimento.setTime(paciente.getDt_nascimento());
			int anoHj = dataHj.get(Calendar.YEAR);
			int anoNascimento = dataNascimento.get(Calendar.YEAR);
			int idade = anoHj - anoNascimento;
			if (idade >= 60) {
				flagRadioButton = true;
				return true;
			}
		} else {
			FacesUtil.addAlerMessage(
					"Paciente sem data de nascimento, sendo impossivel verificar prioridade por idade!");
			return false;
		}
		return false;

	}

	/*
	 * private boolean maiorIdadeMulher(Paciente paciente) { GregorianCalendar
	 * dataHj = new GregorianCalendar(); GregorianCalendar dataNascimento = new
	 * GregorianCalendar();
	 * 
	 * dataNascimento.setTime(paciente.getDt_nascimento()); int anoHj =
	 * dataHj.get(Calendar.YEAR); int anoNascimento =
	 * dataNascimento.get(Calendar.YEAR); int idade = anoHj - anoNascimento;
	 * if(idade >= 60){ return true; } return false; }
	 */

	public void receiturioAlterado(@Observes ReceituarioAlteradoEvent event) {
		this.receituario = event.getReceituario();
	}

	public Status_Prioridade_Requisicao[] getPrioridade_Status() {
		return Status_Prioridade_Requisicao.values();
	}

	public void adicionarRequisicoes() {

	}

	public void removerRequisicao(ItemReceituario itens_req, int linha) {
		this.getReceituario().getItens().remove(itens_req);
		FacesUtil.addInfoMessage("Recibo excluido com sucesso!");
	}

	public void carregarUnidadeLinhaEditavel() {
		ItemReceituario item = this.receituario.getItens().get(0);

		item.setUnidadeSaude(unidadeSaudeEditavel);
		unidadeSaudeEditavel = new UnidadeSaude();

		// this.receituario.adicionarRequisicaoVazio();

	}

	public List<UnidadeSaude> completarUnidade(String nome) {
		return this.unidadeSaudeRepository.porNome(nome);
	}

	public void carregarSolicitacoesFilhas() {
		this.solicitacoes = solicitacaoRepository.solicitacaoDe(solicitaoRaiz);
	}

	private void limpar() {
		receituario = new Receituario();
		solicitacoes = new ArrayList<>();
		//unidadeSaudes = new ArrayList<>();
		this.solicitaoRaiz = null;

		// this.itensRequisicoes = new ItensRequisicoes();
	}

	public void setarItens() {
		// this.itensRequisicoes.setReceituario(this.receituario);
		// this.receituario.getItens().add(itensRequisicoes);
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public Receituario getReceituario() {
		return receituario;
	}

	public void setReceituario(Receituario receituario) {
		this.receituario = receituario;
	}

	/*
	 * public ItensRequisicoes getItensRequisicoes() { return itensRequisicoes;
	 * }
	 * 
	 * public void setItensRequisicoes(ItensRequisicoes itensRequisicoes) {
	 * this.itensRequisicoes = itensRequisicoes; }
	 */

	public List<Solicitacao> getSolicitacaoRaizes() {
		return solicitacaoRaizes;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	/*public List<UnidadeSaude> getUnidadeSaudes() {
		return unidadeSaudes;
	}*/

	public Solicitacao getSolicitaoRaiz() {
		return solicitaoRaiz;
	}

	public void setSolicitaoRaiz(Solicitacao solicitaoRaiz) {
		this.solicitaoRaiz = solicitaoRaiz;
	}

	public UnidadeSaude getUnidadeSaudeEditavel() {
		return unidadeSaudeEditavel;
	}

	public void setUnidadeSaudeEditavel(UnidadeSaude unidadeSaudeEditavel) {
		this.unidadeSaudeEditavel = unidadeSaudeEditavel;
	}

	public ItemReceituario getItensRequisicoes() {
		return itensRequisicoes;
	}

	public void setItensRequisicoes(ItemReceituario itensRequisicoes) {
		this.itensRequisicoes = itensRequisicoes;
	}

	public Date getHojeMais5() {
		Calendar mais5 = Calendar.getInstance();
		mais5.add(Calendar.DAY_OF_MONTH, 5);
		return mais5.getTime();
	}

	public Date getUmAntes() {
		Calendar mais5 = Calendar.getInstance();
		mais5.add(Calendar.YEAR, -1);
		return mais5.getTime();
	}

	public boolean isFlagRadioButton() {
		return flagRadioButton;
	}
	
	
	public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "ejsistemas" +
                                    File.separator + "images" + File.separator + filename + ".jpeg";
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            FacesUtil.addErrorMessage("Error in writing captured image. "+e);
        }
    }
    
    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
         
        return String.valueOf(i);
    }
 
    public String getFilename() {
        return filename;
    }

}
