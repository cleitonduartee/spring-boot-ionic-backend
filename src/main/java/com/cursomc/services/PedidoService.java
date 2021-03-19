package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Pedido;
import com.cursomc.repositories.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public Pedido buscarPorId(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(()-> new IllegalArgumentException("Recurso não encontrado. ID: "+id));
	}
}
