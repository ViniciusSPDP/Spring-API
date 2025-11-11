package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.VendaProduto;
import com.fatec.comercio.models.VendaProdutoKey;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, VendaProdutoKey> {
    
}
