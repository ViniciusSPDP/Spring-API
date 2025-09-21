package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codcep;

    @Column
    private String numerocep;

    public Cep(Integer codcep, String numerocep) {
        this.codcep = codcep;
        this.numerocep = numerocep;
    }

    public Cep() {
    }

    public Integer getCodcep() {
        return codcep;
    }

    public void setCodcep(Integer codcep) {
        this.codcep = codcep;
    }

    public String getNumerocep() {
        return numerocep;
    }

    public void setNumerocep(String numerocep) {
        this.numerocep = numerocep;
    }

}
