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
import com.cursomc.domain.dto.CategoriaDTO;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exception.DataIntegrityException;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundExeception("Recurso não encontrado. ID: "+id));
	}

	public Categoria inserir(Categoria obj) {

		obj.setId(null);
		return repository.save(obj);

	}
	public Categoria atualizar(Categoria obj) {
		Categoria newObj = buscarPorId(obj.getId());
		updateDate(newObj, obj);
		return repository.save(newObj);
	}
	public void deletar(Integer id) {
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

	public Categoria converteCategora(CategoriaDTO objDTO) {

		return new Categoria(objDTO.getId(), objDTO.getNome());

	}
	private void updateDate(Categoria newObj, Categoria obj) {		
		newObj.setNome(obj.getNome());
		
	}
}
