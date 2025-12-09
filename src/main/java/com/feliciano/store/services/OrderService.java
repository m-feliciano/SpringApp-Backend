package com.feliciano.store.services;

import com.feliciano.store.repositories.OrderItemRepository;
import com.feliciano.store.repositories.OrderRepository;
import com.feliciano.store.repositories.PaymentRepository;
import com.feliciano.store.resources.domain.BoletoPayment;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.domain.Order;
import com.feliciano.store.resources.domain.OrderItem;
import com.feliciano.store.resources.domain.enums.PaymentStatus;
import com.feliciano.store.security.SpringSecurityUser;
import com.feliciano.store.services.exceptions.AuthorizationException;
import com.feliciano.store.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BoletoService boletoService;
    private final ProductService prodService;
    private final ClientService clientService;
    private final PaymentRepository pagamento;
    private final OrderItemRepository orderItemRepository;
    private final EmailService emailService;

    @Autowired
    public OrderService(OrderRepository repository,
                        BoletoService boletoService,
                        ProductService prodService,
                        ClientService clientService,
                        PaymentRepository pagamento,
                        OrderItemRepository orderItemRepository,
                        EmailService emailService) {
        this.orderRepository = repository;
        this.boletoService = boletoService;
        this.prodService = prodService;
        this.clientService = clientService;
        this.pagamento = pagamento;
        this.orderItemRepository = orderItemRepository;
        this.emailService = emailService;
    }

    public Order find(Integer id) {
        Optional<Order> obj = orderRepository.findById(id);
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

        if (obj.getPayment() instanceof BoletoPayment boPayment) {
            boletoService.preencherPagamentoComBoleto(boPayment, obj.getInstant());
		}

        obj = orderRepository.save(obj);
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
        if (user == null) throw new AuthorizationException("Access denied!");

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		Client client = clientService.find(user.getId());
        return orderRepository.findByClient(client, pageRequest);

	}
}
