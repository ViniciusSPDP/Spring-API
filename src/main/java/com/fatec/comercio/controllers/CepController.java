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

import com.fatec.comercio.models.Cep;
import com.fatec.comercio.service.CepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ceps")
@Tag(name = "CEP", description = "Endpoints para gerenciar CEPs")
public class CepController {
    
    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @Operation(summary = "Listar todos os CEPs", description = "Retorna uma lista de todos os CEPs cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de CEPs retornada com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("")
    public ResponseEntity<List<Cep>> getCeps() {
        return ResponseEntity.ok(cepService.allCeps());
    }

    @Operation(summary = "Criar um novo CEP", description = "Cria um novo CEP e o salva no banco de dados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CEP criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Requisição inválida."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("")
    public String postCep(@RequestBody Cep cep) {
        cepService.salvarCep(cep);
        return "Cep salvo com sucesso!"; // Idealmente, retornaria ResponseEntity.created(...)
    }

    @Operation(summary = "Deletar um CEP", description = "Remove um CEP do banco de dados pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CEP deletado com sucesso."),
        @ApiResponse(responseCode = "404", description = "CEP não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    public String deleteCep(@PathVariable Integer id) {
        cepService.apagaId(id);
        return "Cep com id " + id + " apagado com sucesso!";
    }

    @Operation(summary = "Atualizar um CEP", description = "Atualiza os dados de um CEP existente com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CEP atualizado com sucesso."),
        @ApiResponse(responseCode = "404", description = "CEP não encontrado."),
        @ApiResponse(responseCode = "400", description = "Requisição inválida."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/{id}")
    public String putCep(@PathVariable Integer id, @RequestBody Cep cep) {
        cepService.editarCep(id, cep);
        return "Cep com id " + id + " atualizado com sucesso!";
    }

    @Operation(summary = "Buscar CEP por ID", description = "Retorna um CEP específico pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CEP encontrado.", content = @Content(schema = @Schema(implementation = Cep.class))),
        @ApiResponse(responseCode = "404", description = "CEP não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cep> getCepById(@PathVariable Integer id) {
        Optional<Cep> cep = Optional.ofNullable(cepService.buscaId(id));
        return cep.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
