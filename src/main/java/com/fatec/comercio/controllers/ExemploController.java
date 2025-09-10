package com.fatec.comercio.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/exemplo")
public class ExemploController {

    @GetMapping
    public String getTodos() {
        return "Bem vindo ao metodo get";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return "Voce quer apagar o id " + id;
    }



}