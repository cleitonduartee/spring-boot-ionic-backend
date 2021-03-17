package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Produto;
import com.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto buscarPorId(Long id) {
		Optional<Produto> prod  = produtoRepository.findById(id);
		return prod.orElse(null);
	}
	
	public List<Produto> listarTodos(){
		List<Produto> list;
		list = produtoRepository.findAll();
		return list;	
	}
}
