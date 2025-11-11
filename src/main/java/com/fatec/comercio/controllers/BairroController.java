package com.fatec.comercio.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Bairro;
import com.fatec.comercio.service.BairroService;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bairros")
@Tag(name = "Bairro", description = "Endpoints para gerenciar bairros")
public class BairroController {

    // Implementação dos endpoints para o recurso "Bairro" (CRUD)
    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @Operation(summary = "Listar todos os bairros", description = "Retorna uma lista de todos os bairros cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de bairros retornada com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("")
    public ResponseEntity<List<Bairro>> getBairros() {
        return ResponseEntity.ok(bairroService.allBairros());
    }

    @Operation(summary = "Criar um novo bairro", description = "Cria um novo bairro e o salva no banco de dados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Bairro criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Requisição inválida."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("")
    public String postBairro(@RequestBody Bairro bairro) {
        bairroService.salvarBairro(bairro);
        return "Bairro salvo com sucesso!"; // Idealmente, retornaria ResponseEntity.created(...)
    }

    @Operation(summary = "Deletar um bairro", description = "Remove um bairro do banco de dados pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bairro deletado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Bairro não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    public String deleteBairro(@PathVariable Integer id) {
        bairroService.apagaId(id);
        return "Bairro com id " + id + " apagado com sucesso!";
    }

    @Operation(summary = "Atualizar um bairro", description = "Atualiza os dados de um bairro existente com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bairro atualizado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Bairro não encontrado."),
        @ApiResponse(responseCode = "400", description = "Requisição inválida."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/{id}")
    public String putBairro(@PathVariable Integer id, @RequestBody Bairro bairro) {
        bairroService.editarBairro(id, bairro);
        return "Bairro com id " + id + " atualizado com sucesso!";
    }

    @Operation(summary = "Buscar bairro por ID", description = "Retorna um bairro específico pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bairro encontrado.", content = @Content(schema = @Schema(implementation = Bairro.class))),
        @ApiResponse(responseCode = "404", description = "Bairro não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Bairro> getBairroById(@PathVariable Integer id) {
        Optional<Bairro> bairro = Optional.ofNullable(bairroService.findBairroById(id));
        return bairro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
