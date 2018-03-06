package com.tcc.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Historico;
import com.tcc.lojavirtual.repository.HistoricoRepository;

@Service
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	
	public Historico insert(Historico obj) {
		obj.setCodigoHistorico(null);
		//obj.setProduto(produtoRepository.findOne(arg0));
		return historicoRepository.save(obj);
	}
	
	public Page<Historico> findPage(Integer page, Integer linesPage, String order, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction),order);
		return historicoRepository.findAll(pageRequest);
	}
	
}
