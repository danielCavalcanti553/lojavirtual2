package com.tcc.lojavirtual.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;

/*
 * 
 * Gera uma nova senha para o cliente e envia por e-mail
 * */
@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	private Random rand = new Random();
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente==null) {
			throw new NotFoundObjectException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(be.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente,newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10;i++) {
			vet[i] = ramdomChar();
		}
		return new String(vet);
	}
	
	// Gerar uma senha forte
	private char ramdomChar() {
		
		int opt = rand.nextInt(3);								// Gera um número inteiro (0,1 ou 2)
		if(opt == 0) return (char) (rand.nextInt(10)+48); 		// gera digito
		else if(opt ==1) return (char) (rand.nextInt(26)+65); 	// gera letra maíuscula
		else  return (char) (rand.nextInt(26)+97);				// gera letra minúscula
		
	}
	
	

}
