package com.tcc.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lojavirtual.domain.Cliente;
import java.lang.String;
import java.util.List;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
