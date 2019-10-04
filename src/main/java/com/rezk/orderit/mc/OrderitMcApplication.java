package com.rezk.orderit.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rezk.orderit.mc.domain.Categoria;
import com.rezk.orderit.mc.repositories.CategoriaRepository;

@SpringBootApplication
public class OrderitMcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderitMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Hardware");
		Categoria cat2 = new Categoria(null, "Software");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
}
