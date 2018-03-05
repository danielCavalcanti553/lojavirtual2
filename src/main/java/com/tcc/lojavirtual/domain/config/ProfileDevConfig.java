package com.tcc.lojavirtual.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tcc.lojavirtual.service.EmailService;
import com.tcc.lojavirtual.service.ProfileTestDados;
import com.tcc.lojavirtual.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class ProfileDevConfig {
	
	@Autowired
	private ProfileTestDados profileTestDados;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String create;
	
	
	@Bean
	public boolean instanciarBD() throws Exception {
		
		if(!create.equals("create")) return false;
		
		profileTestDados.insereDados();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
