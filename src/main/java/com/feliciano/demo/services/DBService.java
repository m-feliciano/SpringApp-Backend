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
