package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    
}
