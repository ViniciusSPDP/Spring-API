package com.fatec.comercio.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Bairro;
import com.fatec.comercio.service.BairroService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/bairros")
public class BairroController {

    // Implementação dos endpoints para o recurso "Bairro" (CRUD)
    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @GetMapping("")
    public List<Bairro> getBairros() {
        return bairroService.allBairros();
    }

    @PostMapping("")
    public String postBairro(@RequestBody Bairro bairro) {
        bairroService.salvarBairro(bairro);
        return "Bairro salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteBairro(@PathVariable Integer id) {
        bairroService.apagaId(id);
        return "Bairro com id " + id + " apagado com sucesso!";
    }

    @PutMapping("/{id}")
    public String putBairro(@PathVariable Integer id, @RequestBody Bairro bairro) {
        bairroService.editarBairro(id, bairro);
        return "Bairro com id " + id + " editado com sucesso!";
    }
    
}
