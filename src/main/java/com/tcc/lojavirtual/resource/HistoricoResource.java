package com.tcc.lojavirtual.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lojavirtual.domain.Historico;
import com.tcc.lojavirtual.dto.HistoricoDTO;
import com.tcc.lojavirtual.service.HistoricoService;

@RestController
@RequestMapping(value="/historico")
public class HistoricoResource {
	@Autowired
	private HistoricoService historicoService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<HistoricoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPage", defaultValue="10")Integer linesPage, 
			@RequestParam(value="order", defaultValue="data")String order, 
			@RequestParam(value="direction", defaultValue="DESC")String direction
			){
		
		Page<Historico> list = historicoService.findPage(page, linesPage, order, direction);
		Page<HistoricoDTO> dto = list.map(obj -> new HistoricoDTO(obj));
		return ResponseEntity.ok().body(dto);
	}
	
}
