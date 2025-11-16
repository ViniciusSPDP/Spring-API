package com.fatec.comercio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.service.SexoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/sexos")
@Tag(name = "Sexo", description = "Endpoints para gerenciar sexos")
public class SexoController {

    private final SexoService sexoService;

    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @Operation(summary = "Obter todos os sexos", description = "Retorna uma lista de todos os sexos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sexos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("")
    public List<Sexo> getSexos() {
        return sexoService.allSexos();
    }

    @Operation(summary = "Cria um novo sexo", description = "Cria um novo sexo e o salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sexo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("")
    public String postSexo(@RequestBody Sexo sexo) {
        sexoService.salvarSexo(sexo);
        return "Sexo salvo com sucesso!";
    }

    @Operation(summary = "Deleta um sexo", description = "Remove um sexo do banco de dados pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sexo apagado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public String deleteSexo(@PathVariable Integer id) {
        sexoService.apagaId(id);
        return "Sexo com id " + id + " apagado com sucesso!";
    }

    @Operation(summary = "Atualiza um sexo", description = "Atualiza os dados de um sexo existente com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sexo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sexo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public String putSexo(@PathVariable Integer id, @RequestBody Sexo sexo) {
        sexoService.editarSexo(id, sexo);
        return "Sexo com id " + id + " editado com sucesso!";
    }

    @Operation(summary = "Obter um sexo pelo ID", description = "Retorna um sexo específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sexo encontrado", content = @Content(schema = @Schema(implementation = Sexo.class))),
            @ApiResponse(responseCode = "404", description = "Sexo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sexo> getSexoById(@PathVariable Integer id) {
        Optional<Sexo> sexo = Optional.ofNullable(sexoService.findSexoById(id));
        return sexo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
}
