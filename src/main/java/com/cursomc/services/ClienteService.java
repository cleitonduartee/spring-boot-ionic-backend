package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.dto.ClienteDTO;
import com.cursomc.domain.dto.ClienteNewDTO;
import com.cursomc.domain.enuns.TipoCliente;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.services.exception.DataIntegrityException;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundExeception("Recurso não encontrado. ID: "+id));
	}
	@Transactional
	public Cliente inserir(Cliente obj) {

		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;

	}
	public Cliente atualizar(Cliente obj) {
		Cliente newObj = buscarPorId(obj.getId());
		updateDate(newObj, obj);
		return repository.save(newObj);
	}
	public void deletar(Long id) {
		buscarPorId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Cliente que tenha pedidos.");
		}
	}
	public List<Cliente> buscarTodos(){
		return	repository.findAll();		
	}
	public Page<Cliente> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente converteClienteDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);

	}
	public Cliente converteClienteDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOUCnpj(),
				TipoCliente.ConverteEnun(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cid, cli);
		
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;

	}
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
		
	}
}
