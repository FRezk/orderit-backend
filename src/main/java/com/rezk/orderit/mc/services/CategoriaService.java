package com.rezk.orderit.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rezk.orderit.mc.domain.Categoria;
import com.rezk.orderit.mc.repositories.CategoriaRepository;
import com.rezk.orderit.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado id: " + id + " tipo: " + Categoria.class.getSimpleName()));
	}

}
