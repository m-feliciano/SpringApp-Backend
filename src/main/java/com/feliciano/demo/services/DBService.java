package com.feliciano.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.feliciano.demo.repositories.AddressRepository;
import com.feliciano.demo.repositories.CategoryRepository;
import com.feliciano.demo.repositories.CityRepository;
import com.feliciano.demo.repositories.ClientRepository;
import com.feliciano.demo.repositories.OrderItemRepository;
import com.feliciano.demo.repositories.OrderRepository;
import com.feliciano.demo.repositories.PaymentRepository;
import com.feliciano.demo.repositories.ProductRepository;
import com.feliciano.demo.repositories.StateRepository;
import com.feliciano.demo.resources.domain.Address;
import com.feliciano.demo.resources.domain.BoletoPayment;
import com.feliciano.demo.resources.domain.CardPayment;
import com.feliciano.demo.resources.domain.Category;
import com.feliciano.demo.resources.domain.City;
import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.resources.domain.Order;
import com.feliciano.demo.resources.domain.OrderItem;
import com.feliciano.demo.resources.domain.Payment;
import com.feliciano.demo.resources.domain.Product;
import com.feliciano.demo.resources.domain.State;
import com.feliciano.demo.resources.domain.enums.ClientType;
import com.feliciano.demo.resources.domain.enums.PaymentStatus;
import com.feliciano.demo.resources.domain.enums.Perfil;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private BCryptPasswordEncoder per;

	public void instantiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Casa, mesa e banho");
		Category cat4 = new Category(null, "Decoração");
		Category cat5 = new Category(null, "Games");
		Category cat6 = new Category(null, "Perfumaria");
		Category cat7 = new Category(null, "Farmacia");

		Product prod1 = new Product(null, "Computador", 2999.0);
		Product prod2 = new Product(null, "Impressora", 599.0);
		Product prod3 = new Product(null, "Teclado", 245.0);
		Product prod4 = new Product(null, "Mesa", 569.0);
		Product prod5 = new Product(null, "Monitor", 999.5);
		Product prod6 = new Product(null, "Perfume", 105.8);
		Product prod7 = new Product(null, "Sabonete", 9.25);
		Product prod8 = new Product(null, "Toalha", 19.30);
		Product prod9 = new Product(null, "Tinta", 45.9);

		// infinity scroll
		Product p12 = new Product(null, "Produto 12", 10.00);
		Product p13 = new Product(null, "Produto 13", 10.00);
		Product p14 = new Product(null, "Produto 14", 10.00);
		Product p15 = new Product(null, "Produto 15", 10.00);
		Product p16 = new Product(null, "Produto 16", 10.00);
		Product p17 = new Product(null, "Produto 17", 10.00);
		Product p18 = new Product(null, "Produto 18", 10.00);
		Product p19 = new Product(null, "Produto 19", 10.00);
		Product p20 = new Product(null, "Produto 20", 10.00);
		Product p21 = new Product(null, "Produto 21", 10.00);
		Product p22 = new Product(null, "Produto 22", 10.00);
		Product p23 = new Product(null, "Produto 23", 10.00);
		Product p24 = new Product(null, "Produto 24", 10.00);
		Product p25 = new Product(null, "Produto 25", 10.00);
		Product p26 = new Product(null, "Produto 26", 10.00);
		Product p27 = new Product(null, "Produto 27", 10.00);
		Product p28 = new Product(null, "Produto 28", 10.00);
		Product p29 = new Product(null, "Produto 29", 10.00);
		Product p30 = new Product(null, "Produto 30", 10.00);
		Product p31 = new Product(null, "Produto 31", 10.00);
		Product p32 = new Product(null, "Produto 32", 10.00);
		Product p33 = new Product(null, "Produto 33", 10.00);
		Product p34 = new Product(null, "Produto 34", 10.00);
		Product p35 = new Product(null, "Produto 35", 10.00);
		Product p36 = new Product(null, "Produto 36", 10.00);
		Product p37 = new Product(null, "Produto 37", 10.00);
		Product p38 = new Product(null, "Produto 38", 10.00);
		Product p39 = new Product(null, "Produto 39", 10.00);
		Product p40 = new Product(null, "Produto 40", 10.00);
		Product p41 = new Product(null, "Produto 41", 10.00);
		Product p42 = new Product(null, "Produto 42", 10.00);
		Product p43 = new Product(null, "Produto 43", 10.00);
		Product p44 = new Product(null, "Produto 44", 10.00);
		Product p45 = new Product(null, "Produto 45", 10.00);
		Product p46 = new Product(null, "Produto 46", 10.00);
		Product p47 = new Product(null, "Produto 47", 10.00);
		Product p48 = new Product(null, "Produto 48", 10.00);
		Product p49 = new Product(null, "Produto 49", 10.00);
		Product p50 = new Product(null, "Produto 50", 10.00);
		// infinity scroll
		cat1.getProducts()
				.addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
						p48, p49, p50));
		// infinity scroll
		p12.getCategories().add(cat1);
		p13.getCategories().add(cat1);
		p14.getCategories().add(cat1);
		p15.getCategories().add(cat1);
		p16.getCategories().add(cat1);
		p17.getCategories().add(cat1);
		p18.getCategories().add(cat1);
		p19.getCategories().add(cat1);
		p20.getCategories().add(cat1);
		p21.getCategories().add(cat1);
		p22.getCategories().add(cat1);
		p23.getCategories().add(cat1);
		p24.getCategories().add(cat1);
		p25.getCategories().add(cat1);
		p26.getCategories().add(cat1);
		p27.getCategories().add(cat1);
		p28.getCategories().add(cat1);
		p29.getCategories().add(cat1);
		p30.getCategories().add(cat1);
		p31.getCategories().add(cat1);
		p32.getCategories().add(cat1);
		p33.getCategories().add(cat1);
		p34.getCategories().add(cat1);
		p35.getCategories().add(cat1);
		p36.getCategories().add(cat1);
		p37.getCategories().add(cat1);
		p38.getCategories().add(cat1);
		p39.getCategories().add(cat1);
		p40.getCategories().add(cat1);
		p41.getCategories().add(cat1);
		p42.getCategories().add(cat1);
		p43.getCategories().add(cat1);
		p44.getCategories().add(cat1);
		p45.getCategories().add(cat1);
		p46.getCategories().add(cat1);
		p47.getCategories().add(cat1);
		p48.getCategories().add(cat1);
		p49.getCategories().add(cat1);
		p50.getCategories().add(cat1);

		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2, prod4, prod5));
		cat3.getProducts().addAll(List.of(prod8));
		cat4.getProducts().addAll(Arrays.asList(prod9, prod4));
		cat5.getProducts().addAll(Arrays.asList(prod3, prod5));
		cat6.getProducts().addAll(List.of(prod6));
		cat7.getProducts().addAll(List.of(prod7));

		prod1.getCategories().addAll(Arrays.asList(cat1, cat5));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().addAll(Arrays.asList(cat1, cat5));
		prod4.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod5.getCategories().addAll(Arrays.asList(cat1, cat2, cat5));
		prod6.getCategories().addAll(Arrays.asList(cat6, cat7));
		prod7.getCategories().addAll(Arrays.asList(cat6, cat7));
		prod8.getCategories().addAll(List.of(cat3));
		prod9.getCategories().addAll(List.of(cat4));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, p12, p13,
				p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35,
				p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		State est1 = new State(null, "São Paulo");
		State est2 = new State(null, "Minas Gerais");

		City cid1 = new City(null, "Campinas", est1);
		City cid2 = new City(null, "São Paulo", est1);
		City cid3 = new City(null, "Uberlandia", est2);

		est1.getCities().addAll(Arrays.asList(cid1, cid2));
		est2.getCities().addAll(List.of(cid3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Client cli1 = new Client(null, "Feliciano", "feliciano@keemail.me", "00000000", ClientType.PERSON,
				per.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("202011210", "959510250"));
		cli1.addPerfil(Perfil.CLIENT);

		Client cli2 = new Client(null, "Vitor", "mfeliciano@keemail.me", "00000001", ClientType.PERSON,
				per.encode("123"));
		cli2.getPhones().addAll(Arrays.asList("121434390", "025073837"));
		cli2.addPerfil(Perfil.ADMIN);

		Address end1 = new Address(null, "Rua da Copa", "20", "10 andar", "Praia do Sol", "00499100", cli1, cid3);
		Address end2 = new Address(null, "Rua sono", "564", "casa", "Cafe palace", "32910010", cli1, cid2);
		Address end3 = new Address(null, "Rua sucesso", "99", "Condominio", "Cabine zoo", "3747430", cli2, cid2);

		cli1.getAddress().addAll(Arrays.asList(end1, end2));
		cli2.getAddress().addAll(List.of(end3));

		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(end1, end2, end3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Order ped1 = new Order(cli1, end1, sdf.parse("07/11/2021 02:00"));
		Order ped2 = new Order(cli1, end2, sdf.parse("01/10/2020 16:30"));

		Payment pag1 = new CardPayment(null, PaymentStatus.PAID, ped1, 4);
		ped1.setPayment(pag1);

		Payment pag2 = new BoletoPayment(null, PaymentStatus.PENDING, ped2, sdf.parse("10/11/2021 00:00"), null);
		ped2.setPayment(pag2);

		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pag1, pag2));

		OrderItem ip1 = new OrderItem(ped1, prod1, 0.00, 1, prod1.getPrice());
		OrderItem ip2 = new OrderItem(ped1, prod3, 0.00, 1, prod3.getPrice());
		OrderItem ip3 = new OrderItem(ped2, prod2, 29.90, 1, prod2.getPrice());

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(List.of(ip3));

		prod1.getItens().addAll(List.of(ip1));
		prod2.getItens().addAll(List.of(ip3));
		prod3.getItens().addAll(List.of(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
