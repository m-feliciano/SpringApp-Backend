package com.feliciano.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feliciano.demo.repositories.CategoriaRepository;
import com.feliciano.demo.repositories.CidadeRepository;
import com.feliciano.demo.repositories.ClienteRepository;
import com.feliciano.demo.repositories.EnderecoRepository;
import com.feliciano.demo.repositories.EstadoRepository;
import com.feliciano.demo.repositories.ItemPedidoRepository;
import com.feliciano.demo.repositories.PagamentoRepository;
import com.feliciano.demo.repositories.PedidoRepository;
import com.feliciano.demo.repositories.ProdutoRepository;
import com.feliciano.demo.resources.domain.Categoria;
import com.feliciano.demo.resources.domain.Cidade;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.resources.domain.Endereco;
import com.feliciano.demo.resources.domain.Estado;
import com.feliciano.demo.resources.domain.EstadoPagamento;
import com.feliciano.demo.resources.domain.ItemPedido;
import com.feliciano.demo.resources.domain.Pagamento;
import com.feliciano.demo.resources.domain.PagamentoComBoleto;
import com.feliciano.demo.resources.domain.PagamentoComCartao;
import com.feliciano.demo.resources.domain.Pedido;
import com.feliciano.demo.resources.domain.Produto;
import com.feliciano.demo.resources.domain.enums.TipoCliente;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().add(cat1);
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");

		Cidade cid1 = new Cidade(null, "Campinas", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est1);
		Cidade cid3 = new Cidade(null, "Uberlandia", est2);

		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		est2.getCidades().addAll(Arrays.asList(cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Maria", "maria@maria.com", "00000000", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("202011210", "959510250"));

		Endereco end1 = new Endereco(null, "Rua da Copa", "20", "10 andar", "Praia do Sol", "00499100", cid3, cli1);
		Endereco end2 = new Endereco(null, "Rua sono", "564", "casa", "Cafe palace", "32910010", cid2, cli1);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

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

		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2999.0);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 1, 599.0);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 29.90, 1, 245.0);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
