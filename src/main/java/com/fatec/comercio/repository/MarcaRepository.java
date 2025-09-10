package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    Marca findByCodmarca(Integer codmarca);
    Marca findByNomemarca(String nomemarca);
    
}
