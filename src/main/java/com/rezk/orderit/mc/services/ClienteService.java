package com.rezk.orderit.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rezk.orderit.mc.domain.Cliente;
import com.rezk.orderit.mc.repositories.ClienteRepository;
import com.rezk.orderit.mc.services.exceptions.DataIntegrityException;
import com.rezk.orderit.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado id: " + id + " tipo: " + Cliente.class.getSimpleName()));
	}
	
	public Page<Cliente> findPage(Integer page, Integer lines, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, lines, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente update(Cliente cliente) {
		Cliente orm = find(cliente.getId());
		updateData(orm, cliente);
		return repo.save(orm);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cliente id " + id + " has relationships with another data!");
		}
	}
	
	private void updateData(Cliente orm, Cliente updated) {
		orm.setNome(updated.getNome());
		orm.setEmail(updated.getEmail());
	}

}
