package com.ejsistemas.semsa.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ejsistemas.semsa.model.Grupo_usuario;
import com.ejsistemas.semsa.model.Usuario;
import com.ejsistemas.semsa.repository.UsuarioRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioRepository usuarioRepository = CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = usuarioRepository.porEmail(email);
		
		UsuarioSistema user = null;
		
		if(usuario != null && usuario.getStatus().equals("Inativo")){
			FacesUtil.addErrorMessage("Usuário inativo");
		}
		else {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Grupo_usuario grupo : usuario.getGrupos()){
			authorities.add(new SimpleGrantedAuthority(grupo.getGrupo().getNome().toUpperCase()));
		}
		
		return authorities;
	}

}
