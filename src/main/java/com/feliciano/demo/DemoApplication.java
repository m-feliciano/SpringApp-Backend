package com.feliciano.demo;

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
import com.feliciano.demo.repositories.ProdutoRepository;
import com.feliciano.demo.resources.domain.Categoria;
import com.feliciano.demo.resources.domain.Cidade;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.resources.domain.Endereco;
import com.feliciano.demo.resources.domain.Estado;
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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2999.0);
		Produto p2 = new Produto(null, "Impressora", 599.0);
		Produto p3 = new Produto(null, "Teclado", 245.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");

		Cidade cid1 = new Cidade(null, "Campinas", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est1);
		Cidade cid3 = new Cidade(null, "Uberlandia", est2);

		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		est2.getCidades().addAll(Arrays.asList(cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Maria", "maria@maria.com", 
				"00000000", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("202011210", "959510250"));

		Endereco end1 = new Endereco(null, "Rua da Copa", "20", "10 andar", 
				"Praia do Sol", "00499100", cid3, cli1);
		Endereco end2 = new Endereco(null, "Rua sono", "564", "casa", 
				"Cafe palace", "32910010", cid2, cli1);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

	}

}
