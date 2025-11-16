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

import com.fatec.comercio.models.Rua;
import com.fatec.comercio.service.RuaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ruas")
@Tag(name = "Rua", description = "Endpoints para gerenciar ruas")
public class RuaController {

    private final RuaService ruaService;

    public RuaController(RuaService ruaService) {
        this.ruaService = ruaService;
    }

    @Operation(summary = "Obter todas as ruas", description = "Retorna uma lista de todas as ruas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ruas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("")
    public List<Rua> getRuas() {
        return ruaService.allRuas();
    }

    @Operation(summary = "Cria uma nova rua", description = "Cria uma nova rua e a salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rua criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("")
    public String postRua(@RequestBody Rua rua) {
        ruaService.salvarRua(rua);
        return "Rua salva com sucesso!";
    }

    @Operation(summary = "Deleta uma rua", description = "Remove uma rua do banco de dados pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rua apagada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public String deleteRua(@PathVariable Integer id) {
        ruaService.apagaId(id);
        return "Rua com id " + id + " apagada com sucesso!";
    }

    @Operation(summary = "Atualiza uma rua", description = "Atualiza os dados de uma rua existente com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rua atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Rua não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public String putRua(@PathVariable Integer id, @RequestBody Rua rua) {
        ruaService.editarRua(id, rua);
        return "Rua com id " + id + " editada com sucesso!";
    }

    @Operation(summary = "Obter uma rua pelo ID", description = "Retorna uma rua específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rua encontrada", content = @Content(schema = @Schema(implementation = Rua.class))),
            @ApiResponse(responseCode = "404", description = "Rua não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Rua> getRuaById(@PathVariable Integer id) {
        Optional<Rua> rua = Optional.ofNullable(ruaService.ruaId(id));
        return rua.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
