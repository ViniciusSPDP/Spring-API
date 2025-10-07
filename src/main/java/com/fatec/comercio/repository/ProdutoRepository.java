package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    
}
