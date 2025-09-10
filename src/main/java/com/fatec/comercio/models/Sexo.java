package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sexo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codsexo;

    @Column
    private String nomesexo;

    public Sexo(Integer codsexo, String nomesexo) {
        this.codsexo = codsexo;
        this.nomesexo = nomesexo;
    }

    public Sexo() {
    }

    public Integer getCodsexo() {
        return codsexo;
    }

    public void setCodsexo(Integer codsexo) {
        this.codsexo = codsexo;
    }

    public String getNomesexo() {
        return nomesexo;
    }

    public void setNomesexo(String nomesexo) {
        this.nomesexo = nomesexo;
    }




}
