package com.rezk.orderit.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rezk.orderit.mc.domain.Categoria;
import com.rezk.orderit.mc.repositories.CategoriaRepository;
import com.rezk.orderit.mc.services.exceptions.DataIntegrityException;
import com.rezk.orderit.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado id: " + id + " tipo: " + Categoria.class.getSimpleName()));
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repo.save(categoria);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categoria id " + id + " has relationship with another data.");
		}
	}

}
