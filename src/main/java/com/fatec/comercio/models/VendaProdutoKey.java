package com.fatec.comercio.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VendaProdutoKey {

     @Column(name = "codvendafk")
    private Integer codvenda;

    @Column(name = "codprodutofk")
    private Integer codproduto;

    // Construtores, getters, setters, hashCode e equals

    public VendaProdutoKey() {}

    public VendaProdutoKey(Integer codvenda, Integer codproduto) {
        this.codvenda = codvenda;
        this.codproduto = codproduto;
    }

    public Integer getCodvenda() {
        return codvenda;
    }

    public void setCodvenda(Integer codvenda) {
        this.codvenda = codvenda;
    }

    public Integer getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(Integer codproduto) {
        this.codproduto = codproduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaProdutoKey that = (VendaProdutoKey) o;
        return Objects.equals(codvenda, that.codvenda) &&
               Objects.equals(codproduto, that.codproduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codvenda, codproduto);
    }
    
}
