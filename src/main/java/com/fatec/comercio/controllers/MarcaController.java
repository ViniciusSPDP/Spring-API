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

import com.fatec.comercio.models.Marca;
import com.fatec.comercio.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("")
    public List<Marca> getMarcas() {
        return marcaService.allMarcas();
    }

    @PostMapping("")
    public String postMarca(@RequestBody Marca marca) {
        marcaService.salvarMarca(marca);
        return "Marca salva com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteMarca(@PathVariable Integer id) {
        marcaService.apagaId(id);
        return "Marca com id " + id + " apagada com sucesso!";
    }

    @PutMapping("/{id}")
    public String putMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marcaService.editarMarca(id, marca);
        return "Marca com id " + id + " editada com sucesso!";
    }
    
}
