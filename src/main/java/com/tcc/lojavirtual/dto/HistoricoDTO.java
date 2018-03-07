package com.tcc.lojavirtual.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.lojavirtual.domain.Historico;
import com.tcc.lojavirtual.domain.enums.TipoHistorico;


public class HistoricoDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String data;
	
	private Integer quantidade;
	private String tipo;
	private Integer produtoId;
	private String produto;
	private String imageUrl;

	
	
	public HistoricoDTO() {
	
	}
	public HistoricoDTO(Historico hist) {
		super();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.data = formato.format(hist.getData());
		this.quantidade = hist.getQuantidade();
		this.tipo = TipoHistorico.toEnum(hist.getTipo()).getDescricao();
		this.produtoId = hist.getProduto().getCodigoProduto();
		this.produto = hist.getProduto().getNomeProduto();
		
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
