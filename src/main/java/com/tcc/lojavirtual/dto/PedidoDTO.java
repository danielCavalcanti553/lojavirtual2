package com.tcc.lojavirtual.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.lojavirtual.domain.Pagamento;
import com.tcc.lojavirtual.domain.Pedido;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer codigoPedido;
	
	
	private String dataPedido;
	private Pagamento pagamento;
	private Long codigoNfe;
	
	private List<ItemPedidoDTO> itens;
	
	public PedidoDTO(Pedido obj) {
		super();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.codigoPedido = obj.getCodigoPedido();
		this.dataPedido = formato.format(obj.getDataPedido()); 
		this.setPagamento(obj.getPagamento());
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

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
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

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	
	
}
