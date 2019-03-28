package com.ejsistemas.semsa.controller.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class ImageBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StreamedContent st;
	
	public void uploadImagem(byte[] imagem) throws IOException{
		st = new DefaultStreamedContent(new ByteArrayInputStream(imagem));
	}

	public StreamedContent getSt() {
		return st;
	}
	
		
}