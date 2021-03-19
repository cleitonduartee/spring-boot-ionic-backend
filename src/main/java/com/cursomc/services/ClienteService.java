package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Cliente;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundExeception("Recurso não encontrado. ID: "+id));
	}
}
