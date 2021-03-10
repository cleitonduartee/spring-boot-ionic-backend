package com.cursomc.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		List<Categoria> lista = new ArrayList<>();
		Categoria cat1 = new Categoria(1l, "Informática");
		Categoria cat2 = new Categoria(2l, "Escritório");
		lista.add(cat1);
		lista.add(cat2);
		return lista;
	}
}
