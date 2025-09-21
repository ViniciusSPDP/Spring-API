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

import com.fatec.comercio.models.Rua;
import com.fatec.comercio.service.RuaService;

@RestController
@RequestMapping("/ruas")
public class RuaController {

    private final RuaService ruaService;

    public RuaController(RuaService ruaService) {
        this.ruaService = ruaService;
    }


    @GetMapping("")
    public List<Rua> getRuas() {
        return ruaService.allRuas();
    }

    @PostMapping("")
    public String postRua(@RequestBody Rua rua) {
        ruaService.salvarRua(rua);
        return "Rua salva com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteRua(@PathVariable Integer id) {
        ruaService.apagaId(id);
        return "Rua com id " + id + " apagada com sucesso!";
    }

    @PutMapping("/{id}")
    public String putRua(@PathVariable Integer id, @RequestBody Rua rua) {
        ruaService.editarRua(id, rua);
        return "Rua com id " + id + " editada com sucesso!";
    }

    @GetMapping("/{id}")
    public Rua getRuaById(@PathVariable Integer id) {
        return ruaService.ruaId(id);
    }

}
