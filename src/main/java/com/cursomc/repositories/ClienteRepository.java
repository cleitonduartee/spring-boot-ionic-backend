package com.cursomc.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	//Padão de nome para que o SpringDATA implementa a busca automatica
	
	@Transactional()
	Cliente findByEmail(String email);
}
