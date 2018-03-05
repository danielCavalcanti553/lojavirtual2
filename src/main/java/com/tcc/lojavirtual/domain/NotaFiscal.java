package com.tcc.lojavirtual.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer codigoNota;

	@JsonFormat(pattern = "dd/mm/yyyy hh:mm")
	private Date dataNota;
	
	
	private Long numeroNfe;
	
	public void gerarNotaFiscal(){
		Random gerador = new Random();
		this.numeroNfe = gerador.nextLong();
		this.dataNota = new Date();
	}
	public NotaFiscal() {
		
	}
	public NotaFiscal(Integer codigo, Pedido pedido) {
		gerarNotaFiscal();
		this.codigoNota = codigo;
		this.setPedido(pedido);
		gerarNotaFiscal();
	}
	public NotaFiscal(Integer codigoNota, Date dataNota) {
		this.codigoNota = codigoNota;
		this.dataNota = dataNota;
	}


	public Integer getCodigoNota() {
		return codigoNota;
	}

	public void setCodigoNota(Integer codigoNota) {
		this.codigoNota = codigoNota;
	}

	public Date getDataNota() {
		return dataNota;
	}

	public void setDataNota(Date dataNota) {
		this.dataNota = dataNota;
	}
	
	
	public Long getNumeroNfe() {
		return numeroNfe;
	}
	public void setNumeroNfe(Long numeroNfe) {
		this.numeroNfe = numeroNfe;
	}




	@OneToOne
	@JoinColumn(name="pedido_codigo_pedido")
	private Pedido pedido;	
	
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
		result = prime * result + ((codigoNota == null) ? 0 : codigoNota.hashCode());
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
		NotaFiscal other = (NotaFiscal) obj;
		if (codigoNota == null) {
			if (other.codigoNota != null)
				return false;
		} else if (!codigoNota.equals(other.codigoNota))
			return false;
		return true;
	}

}
