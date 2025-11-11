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

import com.fatec.comercio.models.Tipo;
import com.fatec.comercio.service.TipoService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tipos")
@Tag(name = "Tipo de Produto", description = "Endpoints para gerenciar tipos de produtos")
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @Operation(summary = "Obter todos os tipos de produtos", description = "Retorna uma lista de todos os tipos de produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tipos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("")
    public ResponseEntity<List<Tipo>> getTipos() {
        return ResponseEntity.ok(tipoService.allTipos());
    }

    @Operation(summary = "Obter um tipo de produto pelo ID", description = "Retorna um tipo de produto específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo encontrado", content = @Content(schema = @Schema(implementation = Tipo.class))),
            @ApiResponse(responseCode = "404", description = "Tipo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getTipoById(@PathVariable Integer id) {
        Optional<Tipo> tipo = Optional.ofNullable(tipoService.buscaId(id));
        return tipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo tipo de produto", description = "Cria um novo tipo de produto e o salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("")
    public String postTipo(@RequestBody Tipo tipo) {
        tipoService.salvarTipo(tipo);
        return "Tipo salvo com sucesso!"; // Idealmente, retornaria ResponseEntity.created(...)
    }

    @Operation(summary = "Atualiza um tipo de produto", description = "Atualiza os dados de um tipo de produto existente com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public String putTipo(@PathVariable Integer id, @RequestBody Tipo tipo) {
        tipoService.editarTipo(id, tipo);
        return "Tipo com id " + id + " atualizado com sucesso!";
    }

    @Operation(summary = "Deleta um tipo de produto", description = "Remove um tipo de produto do banco de dados pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo apagado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public String deleteTipo(@PathVariable Integer id) {
        tipoService.apagaId(id);
        return "Tipo com id " + id + " apagado com sucesso!";
    }

}
