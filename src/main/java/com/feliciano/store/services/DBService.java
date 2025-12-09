package com.feliciano.store.services;

import com.feliciano.store.repositories.AddressRepository;
import com.feliciano.store.repositories.CategoryRepository;
import com.feliciano.store.repositories.CityRepository;
import com.feliciano.store.repositories.ClientRepository;
import com.feliciano.store.repositories.OrderItemRepository;
import com.feliciano.store.repositories.OrderRepository;
import com.feliciano.store.repositories.PaymentRepository;
import com.feliciano.store.repositories.ProductRepository;
import com.feliciano.store.repositories.StateRepository;
import com.feliciano.store.resources.domain.Address;
import com.feliciano.store.resources.domain.BoletoPayment;
import com.feliciano.store.resources.domain.CardPayment;
import com.feliciano.store.resources.domain.Category;
import com.feliciano.store.resources.domain.City;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.domain.Order;
import com.feliciano.store.resources.domain.OrderItem;
import com.feliciano.store.resources.domain.Payment;
import com.feliciano.store.resources.domain.Product;
import com.feliciano.store.resources.domain.State;
import com.feliciano.store.resources.domain.enums.ClientType;
import com.feliciano.store.resources.domain.enums.PaymentStatus;
import com.feliciano.store.resources.domain.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository prodRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBService(CategoryRepository categoryRepository,
                     ProductRepository prodRepository,
                     StateRepository stateRepository,
                     CityRepository cityRepository,
                     ClientRepository clientRepository,
                     AddressRepository addressRepository,
                     OrderRepository orderRepository,
                     PaymentRepository paymentRepository,
                     OrderItemRepository orderItemRepository,
                     PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.prodRepository = prodRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void instantiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Home");
		Category cat4 = new Category(null, "Games");
		Category cat5 = new Category(null, "Decoration");
		Category cat6 = new Category(null, "Perfumery");
		Category cat7 = new Category(null, "Drugstore");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Office desk", 300.00);
		Product p5 = new Product(null, "Towel", 50.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "True color TV", 1200.00);
		Product p8 = new Product(null, "Brush cutter", 800.00);
		Product p9 = new Product(null, "Lampshade", 100.00);
		Product p10 = new Product(null, "Pending", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		// infinity scroll
		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
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

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(List.of(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(List.of(p9,p10));
		cat7.getProducts().addAll(List.of(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat5));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1, cat5));
		p4.getCategories().addAll(Arrays.asList(cat1, cat2));
		p5.getCategories().addAll(Arrays.asList(cat1, cat2, cat5));
		p6.getCategories().addAll(Arrays.asList(cat6, cat7));
		p7.getCategories().addAll(Arrays.asList(cat6, cat7));
		p8.getCategories().addAll(List.of(cat3));
		p9.getCategories().addAll(List.of(cat4));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		prodRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p12, p13,
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
				passwordEncoder.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("202011210", "959510250"));
		cli1.addPerfil(Perfil.CLIENT);

		Client cli2 = new Client(null, "Vitor", "mfeliciano@keemail.me", "00000001", ClientType.PERSON,
				passwordEncoder.encode("123"));
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

		OrderItem ip1 = new OrderItem(ped1, p1, 0.00, 1, p1.getPrice());
		OrderItem ip2 = new OrderItem(ped1, p3, 0.00, 1, p3.getPrice());
		OrderItem ip3 = new OrderItem(ped2, p2, 29.90, 1, p2.getPrice());

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(List.of(ip3));

		p1.getItens().addAll(List.of(ip1));
		p2.getItens().addAll(List.of(ip3));
		p3.getItens().addAll(List.of(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
