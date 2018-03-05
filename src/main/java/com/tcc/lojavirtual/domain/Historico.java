package com.tcc.lojavirtual.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.tcc.lojavirtual.domain.enums.TipoHistorico;
@Entity
public class Historico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoHistorico;
	private Date data;
	private Integer quantidade;
	private Integer tipo;
	
	@ManyToOne
	@JoinColumn(name="produto_codigoProduto")
	private Produto produto;


	public Historico() {

	}	
	

	public Historico(Integer codigoHistorico, Date data, Integer quantidade, TipoHistorico TipoHist, Produto produto) {
		this.codigoHistorico = codigoHistorico;
		this.data = data;
		this.quantidade = quantidade;
		this.tipo = (TipoHist==null) ? null : TipoHist.getCod(); 
		this.produto = produto;
	}


	public Integer getCodigoHistorico() {
		return codigoHistorico;
	}

	public void setCodigoHistorico(Integer codigoHistorico) {
		this.codigoHistorico = codigoHistorico;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

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





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoHistorico == null) ? 0 : codigoHistorico.hashCode());
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
		Historico other = (Historico) obj;
		if (codigoHistorico == null) {
			if (other.codigoHistorico != null)
				return false;
		} else if (!codigoHistorico.equals(other.codigoHistorico))
			return false;
		return true;
	}
	
	
	
	
}
