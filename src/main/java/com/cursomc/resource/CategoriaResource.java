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

import com.cursomc.domain.Categoria;
import com.cursomc.domain.dto.CategoriaDTO;
import com.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id){
		Categoria cat = service.buscarPorId(id);
		return ResponseEntity.ok().body(cat);
	}
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> buscarTodos(){
		List<Categoria> lista = service.buscarTodos();
		List<CategoriaDTO> listaDTO = lista.stream().map((x)-> new CategoriaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> buscarTodosPorPaginas(@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")	Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "Nome")	String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")	String direction){
		Page<Categoria> lista = service.buscarPorPagina(page, linesPerPage, orderBy, direction);		
		Page<CategoriaDTO> listaDTO = lista.map((x)-> new CategoriaDTO(x));
		return ResponseEntity.ok().body(listaDTO);
	}
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody CategoriaDTO objDTO) {

		Categoria obj = service.converteCategora(objDTO);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar (@PathVariable Integer id,@Valid  @RequestBody CategoriaDTO objDTO){
		Categoria obj = service.converteCategora(objDTO);
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
		
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
