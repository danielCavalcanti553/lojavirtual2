package com.tcc.lojavirtual.dto;

import java.io.Serializable;

import com.tcc.lojavirtual.domain.ItemPedido;

public class ItemPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer quantidade;
	private Double preco;
	private Integer codigoProduto;
	private String nomeProduto;
	
	public ItemPedidoDTO(ItemPedido obj) {
		super();
		this.quantidade = obj.getQuantidade();
		this.preco = obj.getPreco();
		this.codigoProduto = obj.getProduto().getCodigoProduto();
		this.nomeProduto = obj.getProduto().getNomeProduto();
	}

	public ItemPedidoDTO() {

	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	
	
	
}
