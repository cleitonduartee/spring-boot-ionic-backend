package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Produto;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.ProdutoRepository;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository cadegoriaRepository;
	
	public Produto buscarPorId(Integer id) {
		Optional<Produto> obj  = produtoRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundExeception("Recurso não encontrado. ID: "+id));
	}
	
	public List<Produto> listarTodos(){
		List<Produto> list;
		list = produtoRepository.findAll();
		return list;	
	}
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);		
		List<Categoria>categorias = cadegoriaRepository.findAllById(ids);
				
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest );
		
	}
}
