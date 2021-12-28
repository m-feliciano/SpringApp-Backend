package com.feliciano.demo.services;

import com.feliciano.demo.repositories.*;
import com.feliciano.demo.resources.domain.*;
import com.feliciano.demo.resources.domain.enums.EstadoPagamento;
import com.feliciano.demo.resources.domain.enums.Perfil;
import com.feliciano.demo.resources.domain.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder per;

    public void instantiateTestDatabase() throws ParseException {

        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");
        Categoria cat3 = new Categoria(null, "Casa, mesa e banho");
        Categoria cat4 = new Categoria(null, "Decoração");
        Categoria cat5 = new Categoria(null, "Games");
        Categoria cat6 = new Categoria(null, "Perfumaria");
        Categoria cat7 = new Categoria(null, "Farmacia");

        Produto prod1 = new Produto(null, "Computador", 2999.0);
        Produto prod2 = new Produto(null, "Impressora", 599.0);
        Produto prod3 = new Produto(null, "Teclado", 245.0);
        Produto prod4 = new Produto(null, "Mesa", 569.0);
        Produto prod5 = new Produto(null, "Monitor", 999.5);
        Produto prod6 = new Produto(null, "Perfume", 105.8);
        Produto prod7 = new Produto(null, "Sabonete", 9.25);
        Produto prod8 = new Produto(null, "Toalha", 19.30);
        Produto prod9 = new Produto(null, "Tinta", 45.9);

        // infinity scroll
        Produto p12 = new Produto(null, "Produto 12", 10.00);
        Produto p13 = new Produto(null, "Produto 13", 10.00);
        Produto p14 = new Produto(null, "Produto 14", 10.00);
        Produto p15 = new Produto(null, "Produto 15", 10.00);
        Produto p16 = new Produto(null, "Produto 16", 10.00);
        Produto p17 = new Produto(null, "Produto 17", 10.00);
        Produto p18 = new Produto(null, "Produto 18", 10.00);
        Produto p19 = new Produto(null, "Produto 19", 10.00);
        Produto p20 = new Produto(null, "Produto 20", 10.00);
        Produto p21 = new Produto(null, "Produto 21", 10.00);
        Produto p22 = new Produto(null, "Produto 22", 10.00);
        Produto p23 = new Produto(null, "Produto 23", 10.00);
        Produto p24 = new Produto(null, "Produto 24", 10.00);
        Produto p25 = new Produto(null, "Produto 25", 10.00);
        Produto p26 = new Produto(null, "Produto 26", 10.00);
        Produto p27 = new Produto(null, "Produto 27", 10.00);
        Produto p28 = new Produto(null, "Produto 28", 10.00);
        Produto p29 = new Produto(null, "Produto 29", 10.00);
        Produto p30 = new Produto(null, "Produto 30", 10.00);
        Produto p31 = new Produto(null, "Produto 31", 10.00);
        Produto p32 = new Produto(null, "Produto 32", 10.00);
        Produto p33 = new Produto(null, "Produto 33", 10.00);
        Produto p34 = new Produto(null, "Produto 34", 10.00);
        Produto p35 = new Produto(null, "Produto 35", 10.00);
        Produto p36 = new Produto(null, "Produto 36", 10.00);
        Produto p37 = new Produto(null, "Produto 37", 10.00);
        Produto p38 = new Produto(null, "Produto 38", 10.00);
        Produto p39 = new Produto(null, "Produto 39", 10.00);
        Produto p40 = new Produto(null, "Produto 40", 10.00);
        Produto p41 = new Produto(null, "Produto 41", 10.00);
        Produto p42 = new Produto(null, "Produto 42", 10.00);
        Produto p43 = new Produto(null, "Produto 43", 10.00);
        Produto p44 = new Produto(null, "Produto 44", 10.00);
        Produto p45 = new Produto(null, "Produto 45", 10.00);
        Produto p46 = new Produto(null, "Produto 46", 10.00);
        Produto p47 = new Produto(null, "Produto 47", 10.00);
        Produto p48 = new Produto(null, "Produto 48", 10.00);
        Produto p49 = new Produto(null, "Produto 49", 10.00);
        Produto p50 = new Produto(null, "Produto 50", 10.00);
        // infinity scroll
        cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        // infinity scroll
        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
        p15.getCategorias().add(cat1);
        p16.getCategorias().add(cat1);
        p17.getCategorias().add(cat1);
        p18.getCategorias().add(cat1);
        p19.getCategorias().add(cat1);
        p20.getCategorias().add(cat1);
        p21.getCategorias().add(cat1);
        p22.getCategorias().add(cat1);
        p23.getCategorias().add(cat1);
        p24.getCategorias().add(cat1);
        p25.getCategorias().add(cat1);
        p26.getCategorias().add(cat1);
        p27.getCategorias().add(cat1);
        p28.getCategorias().add(cat1);
        p29.getCategorias().add(cat1);
        p30.getCategorias().add(cat1);
        p31.getCategorias().add(cat1);
        p32.getCategorias().add(cat1);
        p33.getCategorias().add(cat1);
        p34.getCategorias().add(cat1);
        p35.getCategorias().add(cat1);
        p36.getCategorias().add(cat1);
        p37.getCategorias().add(cat1);
        p38.getCategorias().add(cat1);
        p39.getCategorias().add(cat1);
        p40.getCategorias().add(cat1);
        p41.getCategorias().add(cat1);
        p42.getCategorias().add(cat1);
        p43.getCategorias().add(cat1);
        p44.getCategorias().add(cat1);
        p45.getCategorias().add(cat1);
        p46.getCategorias().add(cat1);
        p47.getCategorias().add(cat1);
        p48.getCategorias().add(cat1);
        p49.getCategorias().add(cat1);
        p50.getCategorias().add(cat1);

        cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProdutos().addAll(Arrays.asList(prod2, prod4, prod5));
        cat3.getProdutos().addAll(List.of(prod8));
        cat4.getProdutos().addAll(Arrays.asList(prod9, prod4));
        cat5.getProdutos().addAll(Arrays.asList(prod3, prod5));
        cat6.getProdutos().addAll(List.of(prod6));
        cat7.getProdutos().addAll(List.of(prod7));

        prod1.getCategorias().addAll(Arrays.asList(cat1, cat5));
        prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        prod3.getCategorias().addAll(Arrays.asList(cat1, cat5));
        prod4.getCategorias().addAll(Arrays.asList(cat1, cat2));
        prod5.getCategorias().addAll(Arrays.asList(cat1, cat2, cat5));
        prod6.getCategorias().addAll(Arrays.asList(cat6, cat7));
        prod7.getCategorias().addAll(Arrays.asList(cat6, cat7));
        prod8.getCategorias().addAll(List.of(cat3));
        prod9.getCategorias().addAll(List.of(cat4));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9));

        // infinity scroll
        produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        Estado est1 = new Estado(null, "São Paulo");
        Estado est2 = new Estado(null, "Minas Gerais");

        Cidade cid1 = new Cidade(null, "Campinas", est1);
        Cidade cid2 = new Cidade(null, "São Paulo", est1);
        Cidade cid3 = new Cidade(null, "Uberlandia", est2);

        est1.getCidades().addAll(Arrays.asList(cid1, cid2));
        est2.getCidades().addAll(List.of(cid3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        Cliente cli1 = new Cliente(null, "Feliciano", "feliciano@keemail.me", "00000000",
                TipoCliente.PESSOAFISICA, per.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("202011210", "959510250"));
        cli1.addPerfil(Perfil.CLIENTE);

        Cliente cli2 = new Cliente(null, "Vitor", "mfeliciano@keemail.me", "00000001",
                TipoCliente.PESSOAFISICA, per.encode("123"));
        cli2.getTelefones().addAll(Arrays.asList("121434390", "025073837"));
        cli2.addPerfil(Perfil.ADMIN);

        Endereco end1 = new Endereco(null, "Rua da Copa", "20", "10 andar", "Praia do Sol", "00499100", cid3, cli1);
        Endereco end2 = new Endereco(null, "Rua sono", "564", "casa", "Cafe palace", "32910010", cid2, cli1);
        Endereco end3 = new Endereco(null, "Rua sucesso", "99", "Condominio", "Cabine zoo", "3747430", cid2, cli2);

        cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
        cli2.getEnderecos().addAll(List.of(end3));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("07/11/2021 02:00"), cli1, end1);
        Pedido ped2 = new Pedido(null, sdf.parse("01/10/2020 16:30"), cli1, end2);

        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 4);
        ped1.setPagamento(pag1);

        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/11/2021 00:00"),
                null);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

        ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, prod1.getPreco());
        ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 1, prod3.getPreco());
        ItemPedido ip3 = new ItemPedido(ped2, prod2, 29.90, 1, prod2.getPreco());

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(List.of(ip3));

        prod1.getItens().addAll(List.of(ip1));
        prod2.getItens().addAll(List.of(ip3));
        prod3.getItens().addAll(List.of(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }

}
