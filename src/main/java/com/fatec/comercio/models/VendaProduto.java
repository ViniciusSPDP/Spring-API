package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class VendaProduto {

    @EmbeddedId
    private VendaProdutoKey id = new VendaProdutoKey();

    @ManyToOne
    @MapsId("codvenda")
    @JoinColumn(name = "codvendafk")
    @JsonIgnore
    private Venda venda;

    @ManyToOne
    @MapsId("codproduto")
    @JoinColumn(name = "codprodutofk")
    private Produto produto;

    private Integer quantv; // quantidade vendida
    private Double valorv; // valor do produto na venda

    // Getters e Setters

    public VendaProdutoKey getId() {
        return id;
    }

    public void setId(VendaProdutoKey id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantv() {
        return quantv;
    }

    public void setQuantv(Integer quantv) {
        this.quantv = quantv;
    }

    public Double getValorv() {
        return valorv;
    }

    public void setValorv(Double valorv) {
        this.valorv = valorv;
    }
    
}
