package com.tcc.lojavirtual.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPk codigoItem = new ItemPedidoPk();

	private Integer quantidade;
	
	private Double preco;

	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
		codigoItem.setPedido(pedido);
		codigoItem.setProduto(produto);
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public ItemPedido() {
	}
	
	public double getSubTotal() {
		return preco  * quantidade;
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
	
	@JsonIgnore
	public Pedido getPedido() {
		return codigoItem.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		codigoItem.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return codigoItem.getProduto();
	}
	
	public void setProduto(Produto produto) {
		codigoItem.setProduto(produto);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoItem == null) ? 0 : codigoItem.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (codigoItem == null) {
			if (other.codigoItem != null)
				return false;
		} else if (!codigoItem.equals(other.codigoItem))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNomeProduto());
		builder.append(", Quantidade: ");
		builder.append(getQuantidade());
		builder.append(", Preço Unitário: ");
		builder.append(n.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(n.format(getSubTotal()));
		builder.append("\n");
		
		return builder.toString();
	}

	
	
	


	
	

	
}
