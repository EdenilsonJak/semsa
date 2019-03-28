package com.ejsistemas.semsa.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

@ApplicationScoped
public class EntityManagerProducer {
	
	//@PersistenceUnit(unitName = "Responsivo")
	private EntityManagerFactory factoryResponsivo;
	
	//@PersistenceUnit(unitName = "Dvs")
	private EntityManagerFactory factoryDvs;
	
	public EntityManagerProducer(){
		factoryResponsivo = Persistence.createEntityManagerFactory("Responsivo");
        factoryDvs = Persistence.createEntityManagerFactory("Dvs");
	}
	
	@Produces
	@RequestScoped
	@Default
	public Session createEntityManagerResponsivo(){
		return (Session) factoryResponsivo.createEntityManager();
	}
	
	@Produces 
	@RequestScoped
	@Dvs
	public Session createEntityManagerDvs(){
		return (Session) factoryDvs.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes @Any Session manager){
		if(manager.isOpen()){
			manager.close();			
		}
	}

}
