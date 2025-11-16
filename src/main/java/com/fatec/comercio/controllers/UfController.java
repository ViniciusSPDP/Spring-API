package com.fatec.comercio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.service.UfService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.fatec.comercio.models.Uf;

@RestController
@RequestMapping("/ufs")
@Tag(name = "Uf", description = "Endpoints para gerenciar UFs")
public class UfController {

    private final UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    @Operation(summary = "Obter todas as UFs", description = "Retorna uma lista de todas as UFs cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de UFs retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("")
    public List<Uf> getUfs () {
        return ufService.allUfs();
    }

    @Operation(summary = "Cria uma nova UF", description = "Cria uma nova UF e a salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UF criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("")
    public String postUf(@RequestBody Uf uf) {
        ufService.salvarUf(uf);
        return "UF salvo com sucesso!";
    }

    @Operation(summary = "Deleta uma UF", description = "Remove uma UF do banco de dados pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UF apagada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public String deleteUf(@PathVariable Integer id) {
        ufService.deleteUf(id);
        return "UF com id " + id + " apagado com sucesso!";
    }

    @Operation(summary = "Atualiza uma UF", description = "Atualiza os dados de uma UF existente com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UF atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "UF não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public String putUf (@PathVariable Integer id, @RequestBody Uf uf) {
        ufService.editarUf(id, uf);
        return "UF com id " + id + " editado com sucesso!";
    }
    
    @Operation(summary = "Obter uma UF pelo ID", description = "Retorna uma UF específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UF encontrada", content = @Content(schema = @Schema(implementation = Uf.class))),
            @ApiResponse(responseCode = "404", description = "UF não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Uf> getUfId(@PathVariable Integer id) {
        Optional<Uf> uf = Optional.ofNullable(ufService.ufId(id));
        return uf.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
