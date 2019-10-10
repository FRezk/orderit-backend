package com.rezk.orderit.mc;

import java.text.SimpleDateFormat;
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
import com.rezk.orderit.mc.domain.ItemPedido;
import com.rezk.orderit.mc.domain.Pagamento;
import com.rezk.orderit.mc.domain.PagamentoComBoleto;
import com.rezk.orderit.mc.domain.PagamentoComCartao;
import com.rezk.orderit.mc.domain.Pedido;
import com.rezk.orderit.mc.domain.Produto;
import com.rezk.orderit.mc.domain.enums.EstadoPagamento;
import com.rezk.orderit.mc.domain.enums.TipoCliente;
import com.rezk.orderit.mc.repositories.CategoriaRepository;
import com.rezk.orderit.mc.repositories.CidadeRepository;
import com.rezk.orderit.mc.repositories.ClienteRepository;
import com.rezk.orderit.mc.repositories.EnderecoRepository;
import com.rezk.orderit.mc.repositories.EstadoRepository;
import com.rezk.orderit.mc.repositories.ItemPedidoRepository;
import com.rezk.orderit.mc.repositories.PagamentoRepository;
import com.rezk.orderit.mc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderitMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				
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
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
