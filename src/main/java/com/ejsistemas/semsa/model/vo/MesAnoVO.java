package com.ejsistemas.semsa.model.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class MesAnoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private BigInteger qtde;
	
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	public BigInteger getQtde() {
		return qtde;
	}
	public void setQtde(BigInteger qtde) {
		this.qtde = qtde;
	}
	
	
	
	
}