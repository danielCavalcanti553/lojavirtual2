package com.tcc.lojavirtual.domain.enums;


public enum TipoHistorico {
	
	ENTRADA(1,"Entrada"),
	SAIDA(2,"Saida");
	
	private int cod;
	private String descricao;
	
	private TipoHistorico(int cod,String desc) {
		this.cod = cod;
		this.descricao = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoHistorico toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(TipoHistorico t : TipoHistorico.values()) {
			if(cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: "+cod);
		
	}
	
	
	
}
