package com.ejsistemas.semsa.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.ejsistemas.semsa.controller.user.ImageBean;
import com.ejsistemas.semsa.converter.ConverterSHA1;
import com.ejsistemas.semsa.model.Grupo;
import com.ejsistemas.semsa.model.Grupo_usuario;
import com.ejsistemas.semsa.model.Usuario;
import com.ejsistemas.semsa.repository.GrupoRepository;
import com.ejsistemas.semsa.repository.UsuarioRepository;
import com.ejsistemas.semsa.service.CadastroUsuarioService;
import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.util.jsf.FacesUtil;
import com.google.common.io.ByteStreams;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	/**
	 * Cadastro de usuários para acesso ao sistema Desenvolvimento por Edenilson
	 * Mendonça dos Santos
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	CadastroUsuarioService cadastroUsuarioService;
	
	@Inject
	GrupoRepository grupoRepository;
	
	@Inject
	UsuarioRepository usuarioRepository;
	
	@Inject
	ImageBean imageBean;

	private List<Grupo> grupos = new ArrayList<>();

	private Usuario usuario;
	private Grupo grupo;
	private Grupo_usuario grupo_usuario;
	
	private String confereSenha;
	private String chkSenha;
	
	private boolean skip;

	public CadastroUsuarioBean() {
		limpar();
		
		
	}

	public void inicializar() {

		// this.convenioRazies = convenioRepository.raizes();
		// estadosRaizes = estados.raizes();
	}
	
	public void salvar(){
		if(usuario.getSenha() == ""){
			usuario.setSenha(chkSenha);
			if((usuario.getSenha() != "") && (usuario.getSenha() != null)){
				cadastroUsuarioService.salvar(usuario);
				FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso!", ""));
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Senha campo obrigatório!", ""));
			}
			
		}	else{
			
			usuario.setSenha(ConverterSHA1.cipher(usuario.getSenha()));
			if(usuario.getSenha() == null ? confereSenha == null : usuario.getSenha().equals(ConverterSHA1.cipher(confereSenha))){
				if(usuario.getImage() == null){
					try {
						InputStream imagem = facesContext.getExternalContext().getResourceAsStream("/resources/ejsistemas/images/user_default.jpg");
						usuario.setImage(ByteStreams.toByteArray(imagem));
						cadastroUsuarioService.salvar(usuario);
						FacesContext.getCurrentInstance().addMessage(null,
			                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
						
					} catch (Exception e) {
						e.getMessage();
					}
			} else{
				cadastroUsuarioService.salvar(usuario);
				FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
			}
				
			}else {
	            FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage(FacesMessage.SEVERITY_WARN, "As senhas não conferem.", ""));
	        }
			
		}
		
	}
	
	public StreamedContent getImagem() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }else{
	        	imageBean.uploadImagem(usuario.getImage());
				return new DefaultStreamedContent(new ByteArrayInputStream(usuario.getImage()));
				}
	}
	
	public void processFileUpload(FileUploadEvent uploadEvent){
		if(uploadEvent.getFile().getSize() > 0){
			try {
				usuario.setImage(uploadEvent.getFile().getContents());
	            imageBean.uploadImagem(usuario.getImage());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
		
	}
	
	public void limpar(){
		usuario = new Usuario();
		grupo = new Grupo();
		grupo_usuario = new Grupo_usuario();
	}
	
	/*LIMPA OBJETOS NÃO PERMANENTES NO VIEWSCOPED*/
	public void limparObjetos(){
		grupo = new Grupo();
		grupo_usuario = new  Grupo_usuario();
	}
	
	public void removePerfil(Grupo_usuario item, int linha){
		if((item.getGrupo() != null) && (linha > -1)){
			this.usuario.getGrupos().remove(linha);
		}
		
	}

	public void setaGrupoUsuario(){
		if(grupo.getId() != null){
			this.grupo_usuario.setGrupo(grupo);
			//this.grupo_usuario.setUsuario(new Usuario());
			this.grupo_usuario.setUsuario(this.usuario);
			this.usuario.getGrupos().add(this.grupo_usuario);
			limparObjetos();
		}
	}

	public Grupo_usuario getGrupo_usuario() {
		return grupo_usuario;
	}

	public void setGrupo_usuario(Grupo_usuario grupo_usuario) {
		this.grupo_usuario = grupo_usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) throws IOException {		
		this.usuario = usuario;
		if(this.usuario.getSenha() != null){
			imageBean.uploadImagem(usuario.getImage());
			confereSenha = this.usuario.getSenha();
			chkSenha = this.usuario.getSenha();
			this.usuario.getGrupos().iterator();
		}
		
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public List<Grupo> getGrupos() throws NegocioException {

		try {
			if ((grupos == null) || (grupos.size() == 0)) {
				grupos = grupoRepository.grupos();
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		return grupos;
	}
	
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	 public boolean isSkip() {
	        return skip;
	}
	 
	 public String getConfereSenha() {
		return confereSenha;
	}
	 
	 public void setConfereSenha(String confereSenha) {
		this.confereSenha = confereSenha;
	}
	
	public String getChkSenha() {
		return chkSenha;
	}
	
	public void setChkSenha(String chkSenha) {
		this.chkSenha = chkSenha;
	}
	
	 
	 public String onFlowProcess(FlowEvent event) throws IOException {
	        if(skip) {
	            skip = false;   //reset in case user goes back
	            return "confirm";
	        }
	        else {
	        	if(usuario.getImage() != null){
	        		imageBean.uploadImagem(usuario.getImage());
	        	}
	            return event.getNewStep();
	        }
	    }

}
