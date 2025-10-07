package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    
}
