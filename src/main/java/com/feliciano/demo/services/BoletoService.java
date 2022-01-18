package com.feliciano.demo.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.feliciano.demo.resources.domain.BoletoPayment;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(BoletoPayment pagto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDueDate(cal.getTime());
	}

}
