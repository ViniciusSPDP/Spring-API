package com.fatec.comercio.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Cep;
import com.fatec.comercio.service.CepService;

@RestController
@RequestMapping("/ceps")
public class CepController {
    
    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("")
    public List<Cep> getCeps() {
        return cepService.allCeps();
    }

    @PostMapping("")
    public String postCep(@RequestBody Cep cep) {
        cepService.salvarCep(cep);
        return "Cep salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteCep(@PathVariable Integer id) {
        cepService.apagaId(id);
        return "Cep com id " + id + " apagado com sucesso!";
    }

    @PutMapping("/{id}")
    public String putCep(@PathVariable Integer id, @RequestBody Cep cep) {
        cepService.editarCep(id, cep);
        return "Cep com id " + id + " editado com sucesso!";
    }

    @GetMapping("/{id}")
    public Cep getCepById(@PathVariable Integer id) {
        return cepService.buscaId(id);
    }
}
