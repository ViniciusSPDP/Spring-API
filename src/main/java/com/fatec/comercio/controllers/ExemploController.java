package com.fatec.comercio.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/exemplo")
@Tag(name = "Exemplo", description = "Endpoints de exemplo")
public class ExemploController {

    @Operation(summary = "Exemplo de GET", description = "Retorna uma mensagem de boas-vindas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public String getTodos() {
        return "Bem vindo ao metodo get";
    }

    @Operation(summary = "Exemplo de DELETE", description = "Retorna uma mensagem indicando qual ID seria deletado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem de deleção retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return "Voce quer apagar o id " + id;
    }



}