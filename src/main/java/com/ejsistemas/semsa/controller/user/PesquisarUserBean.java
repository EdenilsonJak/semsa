package com.ejsistemas.semsa.controller.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.ejsistemas.semsa.model.Usuario;
import com.ejsistemas.semsa.repository.UsuarioRepository;
import com.ejsistemas.semsa.repository.filter.UsuarioFilter;

@Named
@javax.enterprise.context.SessionScoped
public class PesquisarUserBean implements Serializable {

	/**
	 * Pesquisa de Usuários Desenvolvido por Edenilson Mendonça
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioRepository usuarioRepository;
	
	private UsuarioFilter usuarioFilter;
	private List<Usuario> usuariosFiltrados;
	
	
	public PesquisarUserBean(){
		usuarioFilter = new UsuarioFilter();
	}
	
	public void pesquisar(){
		usuariosFiltrados = usuarioRepository.filtrados(usuarioFilter);
	}
	
	
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	
	public UsuarioFilter getUsuarioFilter() {
		return usuarioFilter;
	}
	
	public StreamedContent getImagem() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }else{
	        	String id = context.getExternalContext().getRequestParameterMap().get("usuario");
			Long chave = Long.parseLong(id);
			
			for(Usuario user : this.getUsuariosFiltrados()){
				if(user.getId() == chave){
					return new DefaultStreamedContent(new ByteArrayInputStream(user.getImage()));
				}
			}		
	        }
		return new DefaultStreamedContent();
	}
	
	

}
