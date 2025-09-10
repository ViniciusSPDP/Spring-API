package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codbairro;

    @Column
    private String nomebairro;

    public Bairro(Integer codbairro, String nomebairro) {
        this.codbairro = codbairro;
        this.nomebairro = nomebairro;
    }

    public Bairro() {
    }

    public Integer getCodbairro() {
        return codbairro;
    }

    public void setCodbairro(Integer codbairro) {
        this.codbairro = codbairro;
    }

    public String getNomebairro() {
        return nomebairro;
    }

    public void setNomebairro(String nomebairro) {
        this.nomebairro = nomebairro;
    }

    
}
