package com.ejsistemas.semsa.model;

import java.util.TimeZone;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class appBean {

	public TimeZone getTimeZone(){
		return TimeZone.getDefault();
	}
}
