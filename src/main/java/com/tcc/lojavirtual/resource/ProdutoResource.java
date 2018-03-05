package com.tcc.lojavirtual.resource;

import java.net.URI;
import java.util.List;

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

import com.tcc.lojavirtual.domain.Produto;
import com.tcc.lojavirtual.dto.ProdutoNewDTO;
import com.tcc.lojavirtual.service.ProdutoService;
import com.tcc.lojavirtual.util.UtilURL;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	// USE CASE: Visualizar Produto
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id){
		Produto obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	/*
	// USE CASE: null
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> list = produtoService.findAll();
		return ResponseEntity.ok().body(list);
	}*/
	
	
	// USE CASE: Visualizar Produtos
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<Produto>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPage", defaultValue="24")Integer linesPage, 
			@RequestParam(value="order", defaultValue="nomeProduto")String order, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			){
		
		Page<Produto> list = produtoService.findPage(page, linesPage, order, direction);
		
		return ResponseEntity.ok().body(list);
	}
	
	
	// USE CASE: Cadastrar Produto
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoNewDTO objDto){
		Produto obj = produtoService.fromDTO(objDto);	
		obj = produtoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigoProduto()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	// USE CASE: Atualizar Produto
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ProdutoNewDTO objDto){
		Produto obj = produtoService.fromDTO(objDto);
		obj.setCodigoProduto(id);
		obj = produtoService.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	// USE CASE: Pesquisar Produto
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoNewDTO>> findPage(
			@RequestParam(value="nome", defaultValue="0") String nome,
			@RequestParam(value="categorias", defaultValue="0") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPage", defaultValue="24")Integer linesPage, 
			@RequestParam(value="order", defaultValue="nomeProduto")String order, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			){
		
		List<Integer> ids = UtilURL.intToList(categorias);
		Page<Produto> list = produtoService.search(UtilURL.decodeUrlParam(nome),ids,page, linesPage, order, direction);
		Page<ProdutoNewDTO> listDto = list.map(obj -> new ProdutoNewDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	// USE CASE: Pesquisar Produto Categoria
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoNewDTO>> findPageCategory(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="", defaultValue="24")Integer linesPage, 
			@RequestParam(value="order", defaultValue="nomeProduto")String order, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			){
		
		List<Integer> ids = UtilURL.intToList(categorias);
		Page<Produto> list = produtoService.searchCat(UtilURL.decodeUrlParam(nome),ids,page, linesPage, order, direction);
		Page<ProdutoNewDTO> listDto = list.map(obj -> new ProdutoNewDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}	
	
	// Excluir Produto
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
