package com.ejsistemas.semsa.controller.pericia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.model.Pericia;
import com.ejsistemas.semsa.model.StatusTipoDocumento;
import com.ejsistemas.semsa.model.Status_Pericia;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.PacienteRepository;
import com.ejsistemas.semsa.repository.PericiaRepository;
import com.ejsistemas.semsa.repository.filter.PericiaFilter;
import com.ejsistemas.semsa.service.CadastroPericiaService;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;
import com.ejsistemas.semsa.util.validator.ValidaCPF;

@Named
@ViewScoped
public class PesquisaPericiaBean implements Serializable {

	/**
	 * Desenvolvido por Edenilson Mendonça
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PericiaRepository periciaRepository;
	@Inject
	private PacienteRepository pacienteRepository;
	@Inject
	private FornecedorRepository fornecedorRepository;
	@Inject
	private CadastroPericiaService cadastroPericiaService;
	
	@Inject
	private Pericia periciaDialog;
	@Inject
	private PericiaFilter filter;
	@Inject
	private FacesContext facesContext;
	@Inject
	private EntityManager manager;
	@Inject
	private HttpServletResponse response;
	
	private PericiaFilter filtro = new PericiaFilter();
	
	private LazyDataModel<Pericia> lazyModel;
	private LazyDataModel<Pericia> lazyModelVazio;

	private RequestContext context;
	
	@PostConstruct
	public void init(){
	}
	
	public PesquisaPericiaBean() {
		
		lazyModel = new LazyDataModel<Pericia>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Pericia> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				/*if(filtro.getPaciente() == null){
					return (List<Pericia>) (lazyModelVazio = null);
				}
				
				else{*/
					filtro.setPrimeiroRegistro(first);
					filtro.setQuantidadeRegistros(pageSize);
					
					setRowCount(periciaRepository.quantidadeFiltrados(filtro));
					return periciaRepository.filtrados(filtro);
				//}
			}
			
		};
				
	}
	
	public void salvarEntregar(){
		if(periciaDialog.isExistente()){
			if (periciaDialog.getDoc().equals(StatusTipoDocumento.CPF)){
				if(ValidaCPF.isCPF(periciaDialog.getRgCpf())){
					cadastroPericiaService.salvarEntrega(periciaDialog);
				}else{
					FacesUtil.addErrorMessage("CPF inválido!");
				}
			}else{
				cadastroPericiaService.salvarEntrega(periciaDialog);
			}
			
		}
	}
	
	public void posProcessarXls(Object documento) {
		HSSFWorkbook planilha = (HSSFWorkbook) documento;
		HSSFSheet folha = planilha.getSheetAt(0);
		HSSFRow cabecalho = folha.getRow(0);
		HSSFCellStyle estiloCelula = planilha.createCellStyle();
		Font fonteCabecalho = planilha.createFont();
		
		fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
		fonteCabecalho.setBold(true);
		fonteCabecalho.setFontHeightInPoints((short) 16);
		
		estiloCelula.setFont(fonteCabecalho);
		estiloCelula.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
			cabecalho.getCell(i).setCellStyle(estiloCelula);
		}
	}
	
	
	public List<Fornecedor> pesquisarFornecedor(String campos){
		context = RequestContext.getCurrentInstance();
		List<Fornecedor> fornecedores = fornecedorRepository.porFornecedorFantasia(campos);
		if(fornecedores.size() == 0){
			FacesUtil.addAlerMessage("Estabelecimento não encontrado");
			context.update("msg");
		}
		return fornecedores;
	}
	
	public List<Paciente> pesquisarPacientes(String cammpos){
		context = RequestContext.getCurrentInstance();
		List<Paciente> pacientes = pacienteRepository.porValores(cammpos);
		if(pacientes.size() == 0){
			FacesUtil.addAlerMessage("Paciente não encontrado");
			context.update("msg");
		}
		return pacientes;
	}
	
	public LazyDataModel<Pericia> getLazyModel() {
		return lazyModel;
	}
	
	public LazyDataModel<Pericia> getLazyModelVazio() {
		return lazyModelVazio;
	}
	
	public PericiaFilter getFiltro() {
		return filtro;
	}

	public Pericia getPericiaDialog() {
		return periciaDialog;
	}

	public void setPericiaDialog(Pericia periciaDialog) {
		this.periciaDialog = periciaDialog;
	}
	
	public PericiaFilter getFilter() {
		return filter;
	}

	public void setFilter(PericiaFilter filter) {
		this.filter = filter;
	}

	public void updateComponentes(){
		context = RequestContext.getCurrentInstance();
		ArrayList<String> mylist = new ArrayList<String>(){
			{
				add(":frmEntrega:cpf");
				add(":frmEntrega:rg");
			}
		};
		///context.update(mylist);
		
	}
	
	public void imprimirDemanda() {
		this.filter.setStatus(Status_Pericia.ENTREGUE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		// String image = "/resources/images/logo.jpg";

		parametros.put("status", this.filter.getStatus().getDescricao());
		parametros.put("data_inicio", this.filter.getDataAntes());
		parametros.put("data_fim", this.filter.getDataDepois());
		parametros.put("logo",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/imagem3.png"));
		parametros.put("logo1",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/Imagem2.png"));
		// parametros.put("idade", paciente.getCalculaIdade());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Rel_Demanda_Periodo.jasper", "Relatório Demanda por Período.pdf",
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

