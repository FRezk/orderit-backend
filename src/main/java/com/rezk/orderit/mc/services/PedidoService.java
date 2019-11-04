package com.rezk.orderit.mc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rezk.orderit.mc.domain.ItemPedido;
import com.rezk.orderit.mc.domain.PagamentoComBoleto;
import com.rezk.orderit.mc.domain.Pedido;
import com.rezk.orderit.mc.domain.enums.EstadoPagamento;
import com.rezk.orderit.mc.repositories.ItemPedidoRepository;
import com.rezk.orderit.mc.repositories.PagamentoRepository;
import com.rezk.orderit.mc.repositories.PedidoRepository;
import com.rezk.orderit.mc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado id: " + id + " tipo: " + Pedido.class.getSimpleName()));
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.00);
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		
		return pedido;
	}

}
