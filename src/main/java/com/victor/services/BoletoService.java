package com.victor.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.victor.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preenchePagamentoComBoleto(PagamentoComBoleto pag, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pag.setDataVencimento(cal.getTime());
	}
}
