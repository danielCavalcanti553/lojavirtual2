package com.tcc.lojavirtual.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.domain.enums.Perfil;
import com.tcc.lojavirtual.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer codigoCliente;
	private String cpf;
	private String nome;
	private String endereco;
	private String municipio;
	private String estado;
	private String telefone;
	private String email;
	private String senha;
	
	private Set<Integer> perfis = new HashSet<>();
	
	public ClienteDTO(Cliente c) {
		this.codigoCliente = c.getCodigoCliente();
		this.cpf = c.getCpf();
		this.nome = c.getNome();
		this.endereco = c.getEndereco();
		this.municipio = c.getMunicipio();
		this.estado = c.getEstado();
		this.telefone = c.getTelefone();
		this.email = c.getEmail();
		
		for(Perfil perfil : c.getPerfis()) {
			this.perfis.add(perfil.getCod());
		}
		
	}

	public ClienteDTO() {
	}
	


	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
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

	public Set<Integer> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Integer> perfis) {
		this.perfis = perfis;
	}
	
	
	
	
	
}
