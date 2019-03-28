package com.ejsistemas.semsa.controller.dvs;

import java.io.Serializable;
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

import com.ejsistemas.semsa.model.dvs.AtividadeEconomica;
import com.ejsistemas.semsa.repository.dvs.AtividadeRepository;
import com.ejsistemas.semsa.repository.filter.dvs.AtividadeFilter;
import com.ejsistemas.semsa.util.jpa.Dvs;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class PesquisaFornecedorDvsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AtividadeRepository atividadeRepository;
	@Inject
	private AtividadeEconomica atividadeEconomica;
	@Inject
	private AtividadeFilter filter;
	@Inject
	private FacesContext facesContext;
	@Inject
	private HttpServletResponse response;
	
	@Inject
	@Dvs
	private EntityManager manager;

	
	
	public PesquisaFornecedorDvsBean(){
	}

	public List<AtividadeEconomica> pesquisarAtividades(String campos){
		List<AtividadeEconomica> atividades = atividadeRepository.porValores(campos);
		if(atividades.size() == 0){
			FacesUtil.addAlerMessage("Nenhuma atividade enconrada");
			
		}
		return atividades;
		
	}


	public AtividadeEconomica getAtividadeEconomica() {
		return atividadeEconomica;
	}

	public void setAtividadeEconomica(AtividadeEconomica atividadeEconomica) {
		this.atividadeEconomica = atividadeEconomica;
	}

	public AtividadeFilter getFilter() {
		return filter;
	}

	public void setFilter(AtividadeFilter filter) {
		this.filter = filter;
	}
	
	
	public void relatorio() {

		Map<String, Object> parametros = new HashMap<String, Object>();
		// String image = "/resources/images/logo.jpg";

		//parametros.put("prioridade", this.requisicaoRecepcaoFilter.getStatus());
		parametros.put("cod_atividade", this.atividadeEconomica.getCdatividadeeconomica());
		parametros.put("logo",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/imagem3.png"));
		parametros.put("logo1",
				facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/Imagem2.png"));
		// parametros.put("idade", paciente.getCalculaIdade());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Rel_Fornecedor_Por_Atividade.jasper", "Relatório Operacional Requisições.pdf",
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
