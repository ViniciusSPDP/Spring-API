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

import com.fatec.comercio.service.UfService;
import com.fatec.comercio.models.Uf;

@RestController
@RequestMapping("/ufs")
public class UfController {

    private final UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    @GetMapping("")
    public List<Uf> getUfs () {
        return ufService.allUfs();
    }

    @PostMapping("")
    public String postUf(@RequestBody Uf uf) {
        ufService.salvarUf(uf);
        return "UF salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteUf(@PathVariable Integer id) {
        ufService.deleteUf(id);
        return "UF com id " + id + " apagado com sucesso!";
    }

    @PutMapping("/{id}")
    public String putUf (@PathVariable Integer id, @RequestBody Uf uf) {
        ufService.editarUf(id, uf);
        return "Sexo com id " + id + " editado com sucesso!";
    }
    
}
