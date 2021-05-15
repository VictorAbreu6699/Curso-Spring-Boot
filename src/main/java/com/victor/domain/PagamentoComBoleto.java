package com.victor.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.victor.domain.enums.EstadoPagamento;
@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto()
	{
		super();
		dataVencimento = null;
		dataPagamento = null;
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento ) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
	
	
	
	
}
