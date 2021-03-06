package com.victor.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
@Entity
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instance;	
		
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;	
		
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;	
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Pedido()
	{
		id = null;
		instance = null;
		cliente = null;
		enderecoDeEntrega = null;
	}

	public Pedido(Integer id, Date instance, Cliente cliente, Endereco enderecoDeEntrega) {
		this.id = id;
		this.instance = instance;		
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public double getValorTotal()
	{
		double soma = 0.0;
		
		for (ItemPedido x : itens) {
			soma = soma + x.getSubTotal();
		}
		
		return soma;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstance() {
		return instance;
	}

	public void setInstance(Date instance) {
		this.instance = instance;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		NumberFormat nfFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido n??mero: ");
		builder.append(id);
		builder.append(", Instante: ");
		builder.append(sdFormat.format(instance));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situa????o do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		
		for (ItemPedido x: getItens()) {
			builder.append(x.toString());
		}
		
		builder.append("Valor total: ");
		builder.append(nfFormat.format(getValorTotal())); 
		return builder.toString();
	}
	
	
}
