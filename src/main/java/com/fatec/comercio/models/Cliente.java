package com.fatec.comercio.models;

import java.util.Date; // <-- Verifique se a importação é java.util.Date
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codcliente;

    private String nomecliente;
    private String cpf;
    
    @Temporal(TemporalType.DATE) // <-- Anotação importante para mapear a data
    private Date datanasc;

    private String numerocasa;

    @ManyToOne
    @JoinColumn(name = "codsexofk")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "codbairrofk")
    private Bairro bairro;
    
    @ManyToOne
    @JoinColumn(name = "codcidadefk")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "codruafk")
    private Rua rua;

    @ManyToOne
    @JoinColumn(name = "codcep")
    private Cep cep;

    // Getters e Setters (verifique se todos estão presentes)

    public Integer getCodcliente() { return codcliente; }
    public void setCodcliente(Integer codcliente) { this.codcliente = codcliente; }
    public String getNomecliente() { return nomecliente; }
    public void setNomecliente(String nomecliente) { this.nomecliente = nomecliente; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNumerocasa() { return numerocasa; }
    public void setNumerocasa(String numerocasa) { this.numerocasa = numerocasa; }
    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }
    public Bairro getBairro() { return bairro; }
    public void setBairro(Bairro bairro) { this.bairro = bairro; }
    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }
    public Rua getRua() { return rua; }
    public void setRua(Rua rua) { this.rua = rua; }
    public Cep getCep() { return cep; }
    public void setCep(Cep cep) { this.cep = cep; }

    // --- GETTER E SETTER CORRIGIDOS/ADICIONADOS ---
    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }
}