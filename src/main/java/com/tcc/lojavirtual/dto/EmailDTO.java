package com.tcc.lojavirtual.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public EmailDTO() {
		
	}
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="email inválido")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
