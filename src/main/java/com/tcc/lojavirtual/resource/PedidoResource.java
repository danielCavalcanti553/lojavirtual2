package com.tcc.lojavirtual.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.lojavirtual.domain.Pedido;
import com.tcc.lojavirtual.dto.PedidoDTO;
import com.tcc.lojavirtual.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	// USE CASE: Visualiza Pedido
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		Pedido obj = pedidoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// USE CASE: NULL
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> list = pedidoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	// USE CASE: Realizar Pedido
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Pedido obj){
		
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigoPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	// USE CASE: Visualizar Pedidos (Paginado)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<PedidoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPage", defaultValue="24")Integer linesPage, 
			@RequestParam(value="order", defaultValue="dataPedido")String order, 
			@RequestParam(value="direction", defaultValue="DESC")String direction
			){
		
		Page<Pedido> list = pedidoService.findPage(page, linesPage, order, direction);
		Page<PedidoDTO> listDto = list.map(obj -> new PedidoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
