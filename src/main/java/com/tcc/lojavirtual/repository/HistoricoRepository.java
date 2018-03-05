package com.tcc.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.lojavirtual.domain.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico,Integer>{

}
