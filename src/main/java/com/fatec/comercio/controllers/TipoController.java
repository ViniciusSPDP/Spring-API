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

import com.fatec.comercio.models.Tipo;
import com.fatec.comercio.service.TipoService;

@RestController
@RequestMapping("/tipos")
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping("")
    public List<Tipo> getTipos() {
        return tipoService.allTipos();
    }

    @PostMapping("")
    public String postTipo(@RequestBody Tipo tipo) {
        tipoService.salvarTipo(tipo);
        return "Tipo salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteTipo(@PathVariable Integer id) {
        tipoService.apagaId(id);
        return "Tipo com id " + id + " apagado com sucesso!";
    }

    @PutMapping("/{id}")
    public String putTipo(@PathVariable Integer id, @RequestBody Tipo tipo) {
        tipoService.editarTipo(id, tipo);
        return "Tipo com id " + id + " editado com sucesso!";
    }

    
}
