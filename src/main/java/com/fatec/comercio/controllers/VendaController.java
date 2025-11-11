package com.fatec.comercio.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.comercio.dto.VendaForm;
import com.fatec.comercio.models.Venda;
import com.fatec.comercio.service.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<Venda>> getAllVendas() {
        return ResponseEntity.ok(vendaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Integer id) {
        return vendaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@Valid @RequestBody VendaForm vendaForm) {
        Venda novaVenda = vendaService.save(vendaForm);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaVenda.getCodvenda())
                .toUri();

        return ResponseEntity.created(location).body(novaVenda);
    }

    @DeleteMapping("/{id}")
    public String deleteVenda(@PathVariable Integer id) {
        vendaService.deleteById(id);
        return "Venda com id " + id + " apagada com sucesso!";
    }
    
}
