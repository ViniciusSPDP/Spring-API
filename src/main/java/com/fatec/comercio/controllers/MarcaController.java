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

import com.fatec.comercio.models.Marca;
import com.fatec.comercio.service.MarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/marcas")
@Tag(name = "Marca", description = "Endpoints para gerenciar marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @Operation(summary = "Obter todas as marcas", description = "Retorna uma lista de todas as marcas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("")
    public List<Marca> getMarcas() {
        return marcaService.allMarcas();
    }

    @Operation(summary = "Cria uma nova marca", description = "Cria uma nova marca e a salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("")
    public String postMarca(@RequestBody Marca marca) {
        marcaService.salvarMarca(marca);
        return "Marca salva com sucesso!";
    }

    @Operation(summary = "Deleta uma marca", description = "Remove uma marca do banco de dados pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca apagada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public String deleteMarca(@PathVariable Integer id) {
        marcaService.apagaId(id);
        return "Marca com id " + id + " apagada com sucesso!";
    }

    @Operation(summary = "Atualiza uma marca", description = "Atualiza os dados de uma marca existente com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public String putMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marcaService.editarMarca(id, marca);
        return "Marca com id " + id + " editada com sucesso!";
    }
    
    @Operation(summary = "Obter uma marca pelo ID", description = "Retorna uma marca específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada", content = @Content(schema = @Schema(implementation = Marca.class))),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Integer id) {
        Optional<Marca> marca = Optional.ofNullable(marcaService.marcaId(id));
        return marca.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
