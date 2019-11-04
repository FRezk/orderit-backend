package com.rezk.orderit.mc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rezk.orderit.mc.domain.Categoria;
import com.rezk.orderit.mc.domain.Produto;
import com.rezk.orderit.mc.repositories.ProdutoRepository;
import com.rezk.orderit.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado id: " + id + " tipo: " + Produto.class.getSimpleName()));
	}
	
	public Page<Produto> search(String name, List<Integer> ids, Integer page, Integer lines, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, lines, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = ids.stream().map(id -> new Categoria(id, null)).collect(Collectors.toList());
		return repo.findDistinctByNomeContainingAndCategoriasIn(name, categorias, pageRequest);
	}

}
