package com.tcc.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Historico;
import com.tcc.lojavirtual.repository.HistoricoRepository;

@Service
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Autowired
	private HistoricoRepository produtoRepository;	

	
	public Historico insert(Historico obj) {
		obj.setCodigoHistorico(null);
		//obj.setProduto(produtoRepository.findOne(arg0));
		return historicoRepository.save(obj);
	}
	
}
