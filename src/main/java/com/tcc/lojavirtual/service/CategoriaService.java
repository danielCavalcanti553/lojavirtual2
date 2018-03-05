package com.tcc.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.dto.CategoriaDTO;
import com.tcc.lojavirtual.repository.CategoriaRepository;
import com.tcc.lojavirtual.service.exception.DataIntegrityException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Categoria obj = categoriaRepository.findOne(id);
		if(obj==null) throw new NotFoundObjectException("Objeto não encontrado! " + id);
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setCodigoCategoria(null);
		return categoriaRepository.save(obj);
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getCodigoCategoria());
		updateData(newObj, obj);
		return categoriaRepository.save(newObj);
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPage, String order, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction),order);
		return categoriaRepository.findAll(pageRequest);
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNomeCategoria(obj.getNomeCategoria());
		
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNomeCategoria());
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.delete(id);
		}
			catch(DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir uma categoria com produtos");
			}
	}
	
}
