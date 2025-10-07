package com.fatec.comercio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Produto;
import com.fatec.comercio.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createProduto(@RequestBody Produto produto) {
        produtoService.save(produto);
        return "Produto salvo com sucesso!";
    }

    @PutMapping("/{id}")
    public String updateProduto(@PathVariable Integer id, @RequestBody Produto produtoDetails) {
        produtoDetails.setCodproduto(id);
        produtoService.save(produtoDetails);
        return "Produto com id " + id + " editado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteProduto(@PathVariable Integer id) {
        produtoService.deleteById(id);
        return "Produto com id " + id + " apagado com sucesso!";
    }
    
}
