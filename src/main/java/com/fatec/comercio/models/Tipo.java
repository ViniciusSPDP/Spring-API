package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tipo {

    //Codtipo id
    //nometipo String varchar100
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codtipo;

    @Column(length = 100)
    private String nometipo;

    public Tipo() {
    }

    public Tipo(Integer codtipo, String nometipo) {
        this.codtipo = codtipo;
        this.nometipo = nometipo;
    }

    public Integer getCodtipo() {
        return codtipo;
    }

    public void setCodtipo(Integer codtipo) {
        this.codtipo = codtipo;
    }

    public String getNometipo() {
        return nometipo;
    }

    public void setNometipo(String nometipo) {
        this.nometipo = nometipo;
    }

    


    
}
