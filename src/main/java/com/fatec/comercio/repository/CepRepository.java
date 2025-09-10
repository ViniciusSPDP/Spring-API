package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.comercio.models.Cep;

public interface CepRepository  extends JpaRepository<Cep, Integer> {

    Cep findByCodcep(Integer codcep);
    Cep findByNumerocep(String numerocep);

}
