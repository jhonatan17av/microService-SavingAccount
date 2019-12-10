package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceConfig {
	
	@Value("${config.base.service.uri}")
	  private String pathPerson;

	  @Bean
	  public WebClient registrarWebClient() {
	    return WebClient.create(pathPerson);
	  }

}
