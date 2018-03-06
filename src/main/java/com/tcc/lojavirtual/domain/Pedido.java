package com.tcc.lojavirtual.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer codigoPedido;
	
	@JsonFormat(pattern="dd/mm/yyyy hh:mm")
	private Date dataPedido;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private NotaFiscal notaFiscal;
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	
	@ManyToOne
	@JoinColumn(name="cliente_codigo_cliente")
	private Cliente cliente;
	
	
	@OneToMany(mappedBy="codigoItem.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido(Integer codigoPedido, Date dataPedido, Cliente cliente) {
		super();
		this.codigoPedido = codigoPedido;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
	}

	public Pedido() {
	}


	public  double getValorTotal() {
		double soma = 0.0;
		for(ItemPedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}	
		return soma;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPedido == null) ? 0 : codigoPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (codigoPedido == null) {
			if (other.codigoPedido != null)
				return false;
		} else if (!codigoPedido.equals(other.codigoPedido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		//SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido Nùmero: ");
		builder.append(getCodigoPedido());
		builder.append("\nData: ");
		builder.append(getCodigoPedido());
		builder.append("\nCódigo NFE: ");
		builder.append(getNotaFiscal().getNumeroNfe());
		builder.append("\nCliente: ");
		builder.append(getCliente().getNome());
		builder.append("\n\nDetalhes \n");
		for(ItemPedido i : getItens()) {
			builder.append(i.toString());
		}
		builder.append("\n Valor Total: ");
		builder.append(n.format(getValorTotal()));
		return builder.toString();
	}
	
	
	
}
