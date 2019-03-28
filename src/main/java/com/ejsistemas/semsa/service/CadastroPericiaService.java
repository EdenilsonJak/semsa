package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.model.Pericia;
import com.ejsistemas.semsa.model.StatusTipoDocumento;
import com.ejsistemas.semsa.model.Status_Pericia;
import com.ejsistemas.semsa.repository.PericiaRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.ejsistemas.semsa.util.security.Seguranca;
import com.ejsistemas.semsa.util.validator.ValidaCPF;

public class CadastroPericiaService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private PericiaRepository periciaRepository;
	@Inject
	private Seguranca usuario;
	private RequestContext context;
	
	@Transactional
	public Pericia salvar(Pericia pericia){
		pericia.setDt_entrada(new Date());
		pericia.setUsuario_entrada(usuario.getUsuario());
		return periciaRepository.guardar(pericia);
	}
	
	@Transactional
	public void salvarEntrega(Pericia pericia){
		context = RequestContext.getCurrentInstance();
		
		if(pericia.getStatus().equals(Status_Pericia.RECEBIDO) ||
				pericia.getStatus().equals(Status_Pericia.PENDENTE)){
			pericia.setDt_entrega(new Date());
			pericia.setUsuario_saida(usuario.getUsuario());
			pericia.setStatus(Status_Pericia.ENTREGUE);			
			periciaRepository.guardar(pericia);
			context.addCallbackParam("sucesso", true);
		}
		else{
			FacesUtil.addAlerMessage("Pericia não pode ser entregue com o Status " +pericia.getStatus().getDescricao());
		}
		
	}
	
	public Pericia buscarPorId(Long id){
		return periciaRepository.porId(id);
	}
	
	public List<Pericia> listarTodos(){
		return periciaRepository.listarTodos();
	}
	
}
