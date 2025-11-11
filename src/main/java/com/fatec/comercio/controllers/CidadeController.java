package com.fatec.comercio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Cidade;
import com.fatec.comercio.service.CidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cidades")
@Tag(name = "Cidade", description = "Endpoints para consultar cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Operation(summary = "Listar todas as cidades", description = "Retorna uma lista de todas as cidades cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cidades retornada com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping
    public ResponseEntity<List<Cidade>> getAllCidades(){
        return ResponseEntity.ok(cidadeService.findAll());
    }

    @Operation(summary = "Buscar cidade por ID", description = "Retorna uma cidade específica pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cidade encontrada.", content = @Content(schema = @Schema(implementation = Cidade.class))),
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cidade> getCidadeById(@PathVariable Integer id){
        return cidadeService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    
}
