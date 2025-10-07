package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    
}
