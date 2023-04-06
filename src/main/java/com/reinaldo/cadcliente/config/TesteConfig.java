package com.reinaldo.cadcliente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.reinaldo.cadcliente.domain.service.DBService;

@Configuration
@Profile("teste")
public class TesteConfig {
	
	@Autowired
	private DBService dBService;
	
	@Bean 
	public void instanciaDB() {
		this.dBService.instanciaDB();
	}
	
}
