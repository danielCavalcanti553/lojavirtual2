package com.tcc.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.domain.Produto;
import com.tcc.lojavirtual.dto.ProdutoNewDTO;
import com.tcc.lojavirtual.repository.CategoriaRepository;
import com.tcc.lojavirtual.repository.ProdutoRepository;
import com.tcc.lojavirtual.service.exception.DataIntegrityException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;;
@Service
public class ProdutoService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto find(Integer id) {
		Produto obj = produtoRepository.findOne(id);
		
		if(obj==null) {
			throw new NotFoundObjectException("Produto Não encontrado! " +
					id + ", Tipo " + Produto.class.getName());
			}
		
		return obj;
	}
	

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto insert(Produto obj) {
			obj.setCodigoProduto(null);
			return produtoRepository.save(obj);
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPage, String order, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction), order);
		return produtoRepository.findAll(pageRequest);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPage, String order, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction), order);
		//List<Categoria> categorias = categoriaRepository.findAll(ids);
		//return produtoRepository.findDistinctByNomeProdutoContainingAndCategoriasIn(nome,categorias, pageRequest);
		return produtoRepository.findDistinctByNomeProdutoContainingIgnoreCase(nome,pageRequest);
	}
	
	public Page<Produto> searchCat(String nome, List<Integer> ids,Integer page, Integer linesPage, String order, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction), order);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return produtoRepository.findDistinctByNomeProdutoContainingIgnoreCaseAndCategoriasIn(nome,categorias, pageRequest);
	}

	public Produto fromDTO(ProdutoNewDTO objDto) {
		return new Produto(objDto.getCodigoProduto(),objDto.getNomeProduto(), objDto.getQuantidadeEstoque(), objDto.getPreco());
	}
	
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getCodigoProduto());
		updateData(newObj, obj);
		return produtoRepository.save(newObj);
	}

	private void updateData(Produto newObj, Produto obj) {
		newObj.setNomeProduto(obj.getNomeProduto());
		newObj.setPreco(obj.getPreco());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			produtoRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um produto que possui categorias");
		}
	}
	
}
