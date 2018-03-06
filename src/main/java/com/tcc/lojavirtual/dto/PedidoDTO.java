package com.tcc.lojavirtual.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.lojavirtual.domain.Pedido;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer codigoPedido;
	@JsonFormat(pattern="dd/mm/yyyy hh:mm")
	private Date dataPedido;
	private String numeroCartaoPgto;
	private Double valorPgto;
	private Long codigoNfe;
	
	private List<ItemPedidoDTO> itens;
	
	public PedidoDTO(Pedido obj) {
		super();
		this.codigoPedido = obj.getCodigoPedido();
		this.dataPedido = obj.getDataPedido();
		this.numeroCartaoPgto = obj.getPagamento().getNumeroCartao();
		this.valorPgto = obj.getPagamento().getValor();
		this.codigoNfe = obj.getNotaFiscal().getNumeroNfe();
		this.itens = obj.getItens().stream().map(x -> new ItemPedidoDTO(x)).collect(Collectors.toList());
	}

	public PedidoDTO() {
	
	}

	public Integer getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getNumeroCartaoPgto() {
		return numeroCartaoPgto;
	}

	public void setNumeroCartaoPgto(String numeroCartaoPgto) {
		this.numeroCartaoPgto = numeroCartaoPgto;
	}

	public Double getValorPgto() {
		return valorPgto;
	}

	public void setValorPgto(Double valorPgto) {
		this.valorPgto = valorPgto;
	}

	public Long getCodigoNfe() {
		return codigoNfe;
	}

	public void setCodigoNfe(Long codigoNfe) {
		this.codigoNfe = codigoNfe;
	}

	public List<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

	
	
}
