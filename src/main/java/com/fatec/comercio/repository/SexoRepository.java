package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.comercio.models.Sexo;


public interface SexoRepository extends JpaRepository<Sexo, Integer> {

        Sexo findByNomesexo(String nomesexo);
        Sexo findByCodsexo(Integer codsexo);

}
