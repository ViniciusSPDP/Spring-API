package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Rua;

public interface RuaRepository extends JpaRepository<Rua, Integer> {

    Rua findByCodrua(Integer codrua);
    Rua findByNomerua(String nomerua);
}
