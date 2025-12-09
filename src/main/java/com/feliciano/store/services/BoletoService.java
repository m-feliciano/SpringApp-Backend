package com.feliciano.store.services;

import com.feliciano.store.resources.domain.BoletoPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(BoletoPayment pagto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDueDate(cal.getTime());
	}

}
