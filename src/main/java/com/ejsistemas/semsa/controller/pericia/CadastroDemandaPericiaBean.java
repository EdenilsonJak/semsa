package com.ejsistemas.semsa.controller.pericia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.event.PericiaAlteradoEvent;
import com.ejsistemas.semsa.model.Cid10;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.model.Pericia;
import com.ejsistemas.semsa.model.Procedimento;
import com.ejsistemas.semsa.qualificador.edicao.PericiaEdicao;
import com.ejsistemas.semsa.repository.CidRepository;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.service.CadastroFornecedorService;
import com.ejsistemas.semsa.service.CadastroMedicoService;
import com.ejsistemas.semsa.service.CadastroPacienteService;
import com.ejsistemas.semsa.service.CadastroPericiaService;
import com.ejsistemas.semsa.service.CadastroProcedimentoService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDemandaPericiaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPericiaService cadastroPericiaService;
	@Inject
	private CadastroPacienteService cadastroPacienteService;
	@Inject
	private CadastroFornecedorService cadastroFornecedorService;
	@Inject
	private CadastroMedicoService cadastroMedicoService;
	@Inject
	private CadastroProcedimentoService cadastroProcedimentoService;
	@Inject
	private CidRepository cidRepository;
	
	private RequestContext context;
	
	@Inject
	private Pericia pericia;
	
	private Procedimento procedimento = null;

	@Inject
	private FornecedorFilter fornecedorFilter;
	
	public CadastroDemandaPericiaBean(){
		
	}
	
	public void inicializar(){
	}

	public void salvar(){
		cadastroPericiaService.salvar(pericia);
		FacesUtil.addInfoMessage("Demanda registrada com sucesso");
		limpar();
	}
	
	public void limpar(){
		this.procedimento = null;
		this.pericia = new Pericia();
	}
	
	public List<Paciente> pesquisaPaciente(String campos) {
		context = RequestContext.getCurrentInstance();
		List<Paciente> pacientes = cadastroPacienteService.porNomeCns(campos);

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String nome = request.getParameter("paciente_input");
		
		if(nome.equals("")){
			pericia.setPaciente(new Paciente());
			//context.update("panel");
		}
		
		if (pacientes.size() == 0) {
			FacesUtil.addAlerMessage("Nenhum paciente encontrado!");
			context.update("msg");
		}
		return pacientes;

	}
	
	public void inserirProcedimento(){
		if(procedimento != null){
			pericia.setCo_procedimento(procedimento.getCo_procedimento());
			pericia.setNo_procedimento(procedimento.getNo_procedimento());
			procedimento.setCodNomeProcedimento(pericia.getCo_procedimento()+ " - " +pericia.getNo_procedimento());
		}
		context = RequestContext.getCurrentInstance();
		context.update("panelInformacoesOrigem");
	}
	
	public void atualizarComponente() {
		context = RequestContext.getCurrentInstance();
		if (pericia.isPacieneAssociado()) {
			context.update("grid_panel_paciente");
		}
		if(pericia.isEstabelecimentoAssociado()){
			context.update("panelInformacoesOrigem");
		}
		if(pericia.isEstabelecimentoDestinoAssociado()){
			context.update("panelInformacoesDestino");
		}
	}
	
	public void periciaAlterado(@Observes PericiaAlteradoEvent event){
		this.pericia = event.getPericia();
	}
	
	public List<Fornecedor> filtroFornecedor(String campos){
		List<Fornecedor> hospitais = cadastroFornecedorService.porNomeFornecedorFantasia(campos);
		if(hospitais.size() ==0 ){
			FacesUtil.addAlerMessage("Nenhum estabelecimento encontrado");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		return hospitais;
	}
	
	public List<Medico> pesquisaMedicos(String campos){
		List<Medico> medicos = cadastroMedicoService.buscarPorValores(campos);
		if(medicos.size() == 0){
			FacesUtil.addAlerMessage("Nenhum médico encontrado");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		
		return medicos;
		
	}
	
	public List<Procedimento> pesquisaProcedimentoPorCodigoNome(String campos){
		List<Procedimento> procedimentos = cadastroProcedimentoService.listarPorCodigoNome(campos);
		if(procedimentos.size() == 0){
			FacesUtil.addAlerMessage("Nenhum procedimento encontrado!");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("msg");
		}
		return procedimentos; 
	}
	
	public List<Cid10> pesquisaCid(String str) {
		List<Cid10> cid = cidRepository.porNomes(str);
		if (cid.size() == 0) {
			FacesUtil.addAlerMessage("Cid não encontrado!");
		}
		return cid;
	}
	
	public Pericia getPericia() {
		return pericia;
	}
	
	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}
	
	public FornecedorFilter getFornecedorFilter() {
		return fornecedorFilter;
	}
	
	public Procedimento getProcedimento() {
		return procedimento;
	}
	
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
}
