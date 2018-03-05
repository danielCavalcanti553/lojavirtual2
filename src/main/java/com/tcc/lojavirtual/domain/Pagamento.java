package com.tcc.lojavirtual.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)		
	private Integer codigoPagamento;
	
	@JsonFormat(pattern="dd/mm/yyyy hh:mm")
	private Date dataPagamento;
	
	private String numeroCartao;
	
	private double valor;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_codigo_pedido")
	@MapsId
	private Pedido pedido;

	public Pagamento() {
	}

	public Pagamento(Integer codigoPagamento, Date dataPagamento, String numeroCartao, double valor, Pedido pedido) {
		this.codigoPagamento = codigoPagamento;
		this.dataPagamento = dataPagamento;
		this.numeroCartao = numeroCartao;
		this.valor = valor;
		this.pedido = pedido;
	}

	public Integer getCodigoPagamento() {
		return codigoPagamento;
	}

	public void setCodigoPagamento(Integer codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPagamento == null) ? 0 : codigoPagamento.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (codigoPagamento == null) {
			if (other.codigoPagamento != null)
				return false;
		} else if (!codigoPagamento.equals(other.codigoPagamento))
			return false;
		return true;
	}
	
	
	

}
