package com.cursomc.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exception.DataIntegrityException;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscarPorId(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundExeception("Recurso não encontrado. ID: "+id));
	}
	public Categoria inserir (Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
		
	}
	public Categoria atualizar(Categoria obj) {
		buscarPorId(obj.getId());
		return repository.save(obj);
	}
	public void deletar(Long id) {
		buscarPorId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que tenha produto associado.");
		}
	}
	public List<Categoria> buscarTodos(){
		return	repository.findAll();		
	}
	public Page<Categoria> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
}
