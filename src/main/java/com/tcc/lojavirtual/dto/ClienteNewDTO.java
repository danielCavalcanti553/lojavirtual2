package com.tcc.lojavirtual.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.tcc.lojavirtual.services.validation.ClienteNew;

@ClienteNew
public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="CPF obrigatório")
	@CPF
	private String cpf;
	
	@NotEmpty(message="Nome obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")	
	private String nome;
	
	@NotEmpty(message="Endereço obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String endereco;
	
	@NotEmpty(message="Município obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String municipio;
	
	@NotEmpty(message="Estado obrigatório")
	@Length(min=2,max=50,message = "Estado inválido!")
	private String estado;
	
	@NotEmpty(message="Telefone obrigatório")
	private String telefone;
	
	@NotEmpty(message="E-mail obrigatório")
	@Email
	private String email;
	
	@NotEmpty(message="Senha obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String senha;

	public ClienteNewDTO(String cpf, String nome, String endereco, String municipio, String estado,
			String telefone, String email, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.municipio = municipio;
		this.estado = estado;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;

	}

	public ClienteNewDTO() {
		
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	
	
	
	
	
}
