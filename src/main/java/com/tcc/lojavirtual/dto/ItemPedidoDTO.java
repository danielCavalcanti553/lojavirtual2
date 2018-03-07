package com.tcc.lojavirtual.dto;

import java.io.Serializable;

import com.tcc.lojavirtual.domain.ItemPedido;
import com.tcc.lojavirtual.domain.Produto;

public class ItemPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer quantidade;
	private Produto produto;
	
	public ItemPedidoDTO(ItemPedido obj) {
		super();
		this.quantidade = obj.getQuantidade();
		this.setProduto(obj.getProduto());
	}

	public ItemPedidoDTO() {

	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	
}
