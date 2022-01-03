package com.feliciano.demo.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.feliciano.demo.repositories.OrderItemRepository;
import com.feliciano.demo.repositories.OrderRepository;
import com.feliciano.demo.repositories.PaymentRepository;
import com.feliciano.demo.resources.domain.BoletoPayment;
import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.resources.domain.Order;
import com.feliciano.demo.resources.domain.OrderItem;
import com.feliciano.demo.resources.domain.enums.PaymentStatus;
import com.feliciano.demo.security.SpringSecurityUser;
import com.feliciano.demo.services.exceptions.AuthorizationException;
import com.feliciano.demo.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProductService prodService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private PaymentRepository pagamento;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private EmailService emailService;

	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Order.class.getName()));
	}

	@Transactional
	public Order insert(Order obj) {
		obj.setId(null); // it doesn't allow the passing of id to database
		obj.setInstant(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		obj.setClient(clientService.find(obj.getClient().getId()));
		if (obj.getPayment() instanceof BoletoPayment) {
			BoletoPayment pagto = (BoletoPayment) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
		}
		obj = repo.save(obj);
		pagamento.save(obj.getPayment());

		for (OrderItem p : obj.getItens()) {
			p.setDiscount(0.0);
			p.setProduct(prodService.find(p.getProduct().getId()));
			p.setPrice(p.getProduct().getPrice());
			p.setOrder(obj);
		}

		orderItemRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		SpringSecurityUser user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied!");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		Client client = clientService.find(user.getId());
		return repo.findByClient(client, pageRequest);

	}
}
