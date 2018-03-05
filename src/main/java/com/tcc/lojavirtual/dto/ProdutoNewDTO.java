package com.tcc.lojavirtual.dto;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.tcc.lojavirtual.domain.Produto;

public class ProdutoNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer codigoProduto;
	
	@Length(min=5,max=80,message = "O tamanho deve ser entre 5 e 80 caracteres!")	
	private String nomeProduto;
	
	@Min(value=0,message = "Quantidade deve ser um número inteiro, maior que 0 (zero)")
	private Integer quantidadeEstoque;
	
	@DecimalMin(value="0.00",message = "O valor deve ser um número decimal, maior que 0.00")
	private Double preco;

	// CONSTRUTOR
	public ProdutoNewDTO() {
		this.quantidadeEstoque = 0;
	}
	
	public ProdutoNewDTO(Integer codigoProduto, String nomeProduto, Double preco) {
		super();
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.quantidadeEstoque = 0;
		this.preco = preco;
	}
	
	public ProdutoNewDTO(Produto produto) {
		this.codigoProduto = produto.getCodigoProduto();
		this.nomeProduto = produto.getNomeProduto();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.preco = produto.getPreco();
	}
	
	//GET SET
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
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}


	
}
