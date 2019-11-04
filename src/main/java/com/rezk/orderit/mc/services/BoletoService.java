package com.rezk.orderit.mc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.rezk.orderit.mc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instant) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instant);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(calendar.getTime());
	}

}
