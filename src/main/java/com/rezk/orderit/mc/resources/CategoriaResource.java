package com.rezk.orderit.mc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rezk.orderit.mc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Network");
		Categoria cat2 = new Categoria(2, "Office");
		
		List<Categoria> categories = new ArrayList<>();
		categories.add(cat1);
		categories.add(cat2);
		
		return categories;
	}

}
