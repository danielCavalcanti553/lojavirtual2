package com.tcc.lojavirtual.domain.enums;


public enum Perfil {
	// Tipos de perfis do sistema
	// Prefixo ROLE_ é padrão do Spring Boot
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod,String desc) {
		this.cod = cod;
		this.descricao = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(Perfil t : Perfil.values()) {
			if(cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: "+cod);
		
	}
	
	
	
}
