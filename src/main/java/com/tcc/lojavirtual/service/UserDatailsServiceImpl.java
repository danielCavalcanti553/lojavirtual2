package com.tcc.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.security.UserSecurity;

@Service
public class UserDatailsServiceImpl implements UserDetailsService{
	
	
	
	@Autowired
	private ClienteRepository repo;	
	
	// USE CASE: Autenticar
	// Padrão Spring
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Cliente cli = repo.findByEmail(email);
		if(cli==null) throw new UsernameNotFoundException(email); 
		
		return new UserSecurity(cli.getCodigoCliente(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
	}
	
}
