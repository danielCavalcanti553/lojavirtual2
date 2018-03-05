package com.tcc.lojavirtual.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tcc.lojavirtual.service.EmailService;
import com.tcc.lojavirtual.service.MockEmailService;
import com.tcc.lojavirtual.service.ProfileTestDados;

@Configuration
@Profile("test")
public class ProfileTestConfig {
	
	@Autowired
	private ProfileTestDados profileTestDados;
	
	@Bean
	public boolean instanciarBD() throws Exception {
		profileTestDados.insereDados();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
