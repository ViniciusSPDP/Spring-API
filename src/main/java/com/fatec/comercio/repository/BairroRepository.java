package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.comercio.models.Bairro;


public interface BairroRepository extends JpaRepository<Bairro, Integer> {

        Bairro findByNomebairro(String nomebairro);
        Bairro findByCodbairro(Integer codbairro);
        
}
