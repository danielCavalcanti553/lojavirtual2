package com.tcc.lojavirtual.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tcc.lojavirtual.security.UserSecurity;

public class UserService {

	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e) {
			return null;
		}
		
	}
	

}
