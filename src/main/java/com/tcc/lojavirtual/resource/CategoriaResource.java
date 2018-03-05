package com.tcc.lojavirtual.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.dto.CategoriaDTO;
import com.tcc.lojavirtual.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	
	// @PreAuthorize("hasAnyRole('ADMIN')")
	// Autorização somente para usuários que são administradores
	
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	// USE CASE: Visualizar Produtos
	@RequestMapping(value="/produtos",method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> findAllAndProduto(){
		List<Categoria> list = categoriaService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	// USE CASE: Visualizar Categorias
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> dto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	}
	
	// USE CASE: Cadastrar Categoria
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = categoriaService.fromDTO(objDto);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigoCategoria()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	// USE CASE: Atualizar Categoria
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj = categoriaService.fromDTO(objDto);
		obj.setCodigoCategoria(id);
		obj = categoriaService.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigoCategoria()).toUri();
		return ResponseEntity.created(uri).build();
	}

	
	// USE CASE: Excluir Categoria
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// USE CASE: Visualizar Categorias (Alternativo Paginado)
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPage", defaultValue="24")Integer linesPage, 
			@RequestParam(value="order", defaultValue="nomeCategoria")String order, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			){
		
		Page<Categoria> list = categoriaService.findPage(page, linesPage, order, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
}
