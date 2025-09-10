package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.comercio.models.Uf;

public interface UfRepository extends JpaRepository<Uf, Integer> {
    
    //Variacoes de consulta JPARepository
    Uf findByNomeuf(String nomeUf);
    Uf findBySiglauf(String siglaUf);
    Uf findByCoduf(Integer coduf);
}
