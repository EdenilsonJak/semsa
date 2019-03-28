package com.ejsistemas.semsa.util.migracao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class FlywayMigrationDataBase implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(FlywayMigrationDataBase.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        DataSource dataSource, dataSourceDvs;
        dataSource = null;
        dataSourceDvs = null;
        
        Context ctx;

        try {

        	ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/responsivoBD");

        } catch (NamingException e) {
            log.error("context", e);
        }
        
        try {

        	ctx = new InitialContext();
            dataSourceDvs = (DataSource) ctx.lookup("java:comp/env/jdbc/dvsBD");

        } catch (NamingException e) {
            log.error("context", e);
        }

        log.info("Iniciando a verificação e atualização da base de dados...");

        Flyway flyway, flywayDvs;
        flyway = new Flyway();
        flywayDvs = new Flyway();
        
        flyway.setDataSource(dataSource);
        flyway.setLocations("db/migration/mysql");
        
        if (flyway.info().current() == null) {
            flyway.baseline();
        }

        flyway.migrate();
        
        flywayDvs.setDataSource(dataSourceDvs);
        flywayDvs.setLocations("db/migration/mysql_dvs");
        if(flywayDvs.info().current() == null){
        	flywayDvs.baseline();
        }
        
        flywayDvs.migrate();
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // método não utilizado
    }
}