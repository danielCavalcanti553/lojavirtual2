package com.tcc.lojavirtual.dto;

import java.io.Serializable;
import java.util.Date;


public class HistoricoDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private Date data;
	private Integer quantidade;
	private Integer tipo;
	private Integer produtoId;
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Integer getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	
	
	
}
