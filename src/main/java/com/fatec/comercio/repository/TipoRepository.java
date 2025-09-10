package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {

    Tipo findByNometipo(String nometipo);
    Tipo findByCodtipo(Integer codtipo);

}
