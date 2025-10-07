package com.fatec.comercio.models;

import java.util.Date; // <-- Verifique se a importação é java.util.Date
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codvenda;

    @Temporal(TemporalType.DATE) // <-- Anotação importante para mapear a data
    private Date datavenda; // <-- Verifique se o tipo é java.util.Date

    @ManyToOne
    @JoinColumn(name = "codclientefk")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<VendaProduto> produtos;

    // Getters e Setters

    public Integer getCodvenda() { return codvenda; }
    public void setCodvenda(Integer codvenda) { this.codvenda = codvenda; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Set<VendaProduto> getProdutos() { return produtos; }
    public void setProdutos(Set<VendaProduto> produtos) { this.produtos = produtos; }
    
    // --- GETTER E SETTER CORRIGIDOS ---
    public Date getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }
}