package com.cursomc.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;

@Configuration
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		
	Categoria cat1 = new Categoria(null, "Informatica");
	
	Categoria cat2 = new Categoria(null, "Escritório");
	
	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
