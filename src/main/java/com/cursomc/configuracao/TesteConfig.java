package com.cursomc.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Produto;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.ProdutoRepository;

@Configuration
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {
		
	Categoria cat1 = new Categoria(null, "Informatica");	
	Categoria cat2 = new Categoria(null, "Escritório");
	
	
	Produto p1 = new Produto(null,"Computador", 20000.00);
	Produto p2 = new Produto(null,"Impressora", 800.00);
	Produto p3 = new Produto(null,"Mouse", 80.00);
	
	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	
	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	cat2.getProdutos().addAll(Arrays.asList(p2));
	
	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat2,cat1));
	p3.getCategorias().addAll(Arrays.asList(cat1));
	
	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
