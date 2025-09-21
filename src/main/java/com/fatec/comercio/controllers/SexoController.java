package com.fatec.comercio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.service.SexoService;
import java.util.List;

@RestController
@RequestMapping("/sexos")
public class SexoController {

    private final SexoService sexoService;

    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping("")
    public List<Sexo> getSexos() {
        return sexoService.allSexos();
    }

    @PostMapping("")
    public String postSexo(@RequestBody Sexo sexo) {
        sexoService.salvarSexo(sexo);
        return "Sexo salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteSexo(@PathVariable Integer id) {
        sexoService.apagaId(id);
        return "Sexo com id " + id + " apagado com sucesso!";
    }

    @PutMapping("/{id}")
    public String putSexo(@PathVariable Integer id, @RequestBody Sexo sexo) {
        sexoService.editarSexo(id, sexo);
        return "Sexo com id " + id + " editado com sucesso!";
    }

    @GetMapping("/{id}")
    public Sexo getSexoById(@PathVariable Integer id) {
        return sexoService.findSexoById(id);
    }
    
}
