package com.tcc.lojavirtual.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	
	@Transactional(readOnly=true) // Não inicia uma transação, não é necessário, esta sendo efetuado uma consulta
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
