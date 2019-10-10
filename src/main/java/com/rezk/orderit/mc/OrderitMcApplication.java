package com.rezk.orderit.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rezk.orderit.mc.domain.Categoria;
import com.rezk.orderit.mc.domain.Cidade;
import com.rezk.orderit.mc.domain.Cliente;
import com.rezk.orderit.mc.domain.Endereco;
import com.rezk.orderit.mc.domain.Estado;
import com.rezk.orderit.mc.domain.Produto;
import com.rezk.orderit.mc.domain.enums.TipoCliente;
import com.rezk.orderit.mc.repositories.CategoriaRepository;
import com.rezk.orderit.mc.repositories.CidadeRepository;
import com.rezk.orderit.mc.repositories.ClienteRepository;
import com.rezk.orderit.mc.repositories.EnderecoRepository;
import com.rezk.orderit.mc.repositories.EstadoRepository;
import com.rezk.orderit.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class OrderitMcApplication implements CommandLineRunner {
	
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
		SpringApplication.run(OrderitMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Hardware");
		Categoria cat2 = new Categoria(null, "Software");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Windows", 800.00);
		Produto p3 = new Produto(null, "Impressora", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p3);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "39285746173", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("123456789", "987654321"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "020518-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "020518-000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		Cliente cli2 = new Cliente(null, "Fernando Rezk", "frezk@gmail.com", "45791346825", TipoCliente.PESSOA_JURIDICA);
		Endereco e3 = new Endereco(null, "Eurico Gaspar Dultra", "1801", "Apto. 06", "Parada Inglesa", "020518-000", cli1, c2);
		
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		e3.setCliente(cli2);
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
	}
}
