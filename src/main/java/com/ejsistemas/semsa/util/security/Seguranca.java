package com.ejsistemas.semsa.util.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.ejsistemas.semsa.model.Grupo_usuario;
import com.ejsistemas.semsa.model.Modulo_Sistema;
import com.ejsistemas.semsa.model.Usuario;

@Named	
@RequestScoped
public class Seguranca {
	
	@Inject
	private ExternalContext externalContext;
	
	public String getNomeUsuario(){
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if(usuarioLogado != null){
		nome = usuarioLogado.getUsuario().getNome();
		}
		return nome;
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if(auth != null && auth.getPrincipal() != null){
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	public Usuario getUsuario(){
		Usuario usuario = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if(usuarioLogado != null){
			usuario = usuarioLogado.getUsuario();
		}
		return usuario;
	}
	

	public boolean isUsuarioResponsavelRegulacao(){
		return externalContext.isUserInRole("ADMINISTRADOR - REGULACAO")
				|| externalContext.isUserInRole("SUPERVISOR - REGULACAO")
				|| externalContext.isUserInRole("DIGITADOR - REGULACAO");
	}
	
	public boolean isUsuarioResponsavelRecepcaoEntrada(){
		return externalContext.isUserInRole("ADMINISTRADOR - RECEPCAO")
				|| externalContext.isUserInRole("SUPERVISOR - RECEPCAO")
				|| externalContext.isUserInRole("DIGITADOR - RECEPCAO");
	}
	
	public boolean isCancelarInternacaoOcupado(){
		return externalContext.isUserInRole("ADMINISTRADOR - REGULACAO")
				|| externalContext.isUserInRole("SUPERVISOR - REGULACAO")
				|| externalContext.isUserInRole("DIGITADOR - REGULACAO");
	}
	
	
	/*ACESSO AOS MÓDULOS DO SISTEMA*/
	
	public boolean isCadastroUsuarios(){
		return externalContext.isUserInRole("ADMINISTRADOR - CONFIGURACAO");
	}
	
	public boolean isAcessoRecepcao(){
		return externalContext.isUserInRole("DIGITADOR - RECEPCAO")
				|| externalContext.isUserInRole("ADMINISTRADOR - RECEPCAO")
				|| externalContext.isUserInRole("SUPERVISOR - RECEPCAO")
				|| externalContext.isUserInRole("VISITANTE - RECEPCAO");
	}
	
	
	public boolean isAcessoPericia(){
		return externalContext.isUserInRole("DIGITADOR - PERICIA")
				|| externalContext.isUserInRole("ADMINISTRADOR - PERICIA")
				|| externalContext.isUserInRole("SUPERVISOR - PERICIA")
				|| externalContext.isUserInRole("VISITANTE - PERICIA");
	}
	
	public boolean isAcessoRegulacao(){
		return externalContext.isUserInRole("DIGITADOR - REGULACAO")
				|| externalContext.isUserInRole("ADMINISTRADOR - REGULACAO")
				|| externalContext.isUserInRole("SUPERVISOR - REGULACAO")
				|| externalContext.isUserInRole("VISITANTE - REGULACAO");
	}
	
	public boolean isAcessoLicitacao(){
		return externalContext.isUserInRole("DIGITADOR - LICITACAO")
				|| externalContext.isUserInRole("ADMINISTRADOR - LICITACAO")
				|| externalContext.isUserInRole("SUPERVISOR - LICITACAO")
				|| externalContext.isUserInRole("VISITANTE - LICITACAO");
	}
	
	public boolean isAcessoAlmoxarifado(){
		return externalContext.isUserInRole("DIGITADOR - ALMOXARIFADO")
				|| externalContext.isUserInRole("ADMINISTRADOR - ALMOXARIFADO")
				|| externalContext.isUserInRole("SUPERVISOR - ALMOXARIFADO")
				|| externalContext.isUserInRole("VISITANTE - ALMOXARIFADO");
	}
	
	public boolean isAcessoVigilancia(){
		return externalContext.isUserInRole("DIGITADOR - VIGILANCIA")
				|| externalContext.isUserInRole("ADMINISTRADOR - VIGILANCIA")
				|| externalContext.isUserInRole("SUPERVISOR - VIGILANCIA")
				|| externalContext.isUserInRole("VISITANTE - VIGILANCIA");
	}
	
	/*FIM DO BLOCO*/
	
	/*OPÇÕES PARA PERMISSÕES*/
	
	public boolean isAcessoVisitante(){
		return externalContext.isUserInRole("VISITANTE - RECEPCAO")
				|| externalContext.isUserInRole("VISITANTE - REGULACAO")
				|| externalContext.isUserInRole("VISITANTE - ALMOXARIFADO")
				|| externalContext.isUserInRole("VISITANTE - REQUISICAO")
				|| externalContext.isUserInRole("VISITANTE - PERICIA");
	}
	
	public boolean isPermissaoAcoesPericia(){
		return externalContext.isUserInRole("ADMINISTRADOR - PERICIA")
				|| externalContext.isUserInRole("SUPERVISOR - PERICIA");
	}
	
	public boolean isPermissaoAcoesRecepcao(){
		return externalContext.isUserInRole("ADMINISTRADOR - RECEPCAO")
				|| externalContext.isUserInRole("SUPERVISOR - RECEPCAO");
	}
	
	public boolean isPermissaoAcoesRegulacao(){
		return externalContext.isUserInRole("ADMINISTRADOR - REGULACAO")
				|| externalContext.isUserInRole("SUPERVISOR - REGULACAO");
	}
	
	public boolean isPermissaoAcoesAlmoxarifado(){
		return externalContext.isUserInRole("ADMINISTRADOR - ALMOXARIFADO")
				|| externalContext.isUserInRole("SUPERVISOR - ALMOXARIFADO");
	}
	
	public boolean isPermissaoAcoesLicitacao(){
		return externalContext.isUserInRole("ADMINISTRADOR - LICITACAO")
				|| externalContext.isUserInRole("SUPERVISOR - LICITACAO");
	}

	/*FIM DO BLOCO PERMISSÕES*/
	
	
	public boolean isRecepcao(){
		if(getUsuario() != null){
			for (Grupo_usuario grupo_list : getUsuario().getGrupos()) {
				if(grupo_list.getSistema_recepcao().equals(Modulo_Sistema.RECEPCAO)){
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	public boolean isLicitacao(){
		if(getUsuario() != null){
			for(Grupo_usuario list_grupo : getUsuario().getGrupos()){
				if(list_grupo.getSistema_licitacao().equals(Modulo_Sistema.LICITACAO)){
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	public boolean isRegulcao(){
		if(getUsuario() != null){
			for(Grupo_usuario list_grupo : getUsuario().getGrupos()){
				if(list_grupo.getSistema_regulacao().equals(Modulo_Sistema.REGULACAO)){
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	public boolean isAlmoxarifado(){
		if(getUsuario() != null){
			for(Grupo_usuario list_grupo : getUsuario().getGrupos()){
				if(list_grupo.getSistema_almoxarifado().equals(Modulo_Sistema.ALMOXARIFADO)){
					return true;
				}
				break;
			}
		}
		return false;
	}
	
}
