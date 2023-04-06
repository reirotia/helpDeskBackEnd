package com.reinaldo.cadcliente.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	
	
	@Bean
	public CorsFilter corsFilter() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("http://127.0.0.1:4200");
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedOrigin("http://localhost:8080");	    
	    config.addAllowedHeader("*");
	    config.setAllowedMethods(Arrays.asList("OPTIONS", "GET"));

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/actuator/**", config);

	    return new CorsFilter(source);
	}
}
