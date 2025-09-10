package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Marca {

    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    private Integer codmarca;

    @Column(length = 100)
    private String nomemarca;

    public Marca() {
    }

    public Marca(Integer codmarca, String nomemarca) {
        this.codmarca = codmarca;
        this.nomemarca = nomemarca;
    }

    public Integer getCodmarca() {
        return codmarca;
    }

    public void setCodmarca(Integer codmarca) {
        this.codmarca = codmarca;
    }

    public String getNomemarca() {
        return nomemarca;
    }

    public void setNomemarca(String nomemarca) {
        this.nomemarca = nomemarca;
    }

    
    



}
