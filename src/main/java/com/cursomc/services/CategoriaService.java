package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundExeception("Recurso não encontrado. ID: "+id));
	}
	public Categoria inserir (Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
		
	}
}
