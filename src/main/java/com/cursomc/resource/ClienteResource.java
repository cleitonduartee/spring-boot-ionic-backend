package com.cursomc.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.domain.Cliente;
import com.cursomc.domain.dto.ClienteDTO;
import com.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
		Cliente cli = service.buscarPorId(id);
		return ResponseEntity.ok().body(cli);
	}
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarTodos(){
		List<Cliente> lista = service.buscarTodos();
		List<ClienteDTO> listaDTO = lista.stream().map((x)-> new ClienteDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> buscarTodosPorPaginas(@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")	Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "Nome")	String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")	String direction){
		Page<Cliente> lista = service.buscarPorPagina(page, linesPerPage, orderBy, direction);		
		Page<ClienteDTO> listaDTO = lista.map((x)-> new ClienteDTO(x));
		return ResponseEntity.ok().body(listaDTO);
	}
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteDTO objDTO) {

		Cliente obj = service.converteClienteDTO(objDTO);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar (@PathVariable Long id,@Valid  @RequestBody ClienteDTO objDTO){
		Cliente obj = service.converteClienteDTO(objDTO);
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
		
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
