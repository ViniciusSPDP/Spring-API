package com.fatec.comercio.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.comercio.dto.VendaForm;
import com.fatec.comercio.models.Venda;
import com.fatec.comercio.service.VendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Venda", description = "Endpoints para gerenciar vendas")
public class VendaController {

        @Autowired
        private VendaService vendaService;

        @Operation(summary = "Obter todas as vendas", description = "Retorna uma lista de todas as vendas cadastradas")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de vendas retornada com sucesso"),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
        @GetMapping
        public ResponseEntity<List<Venda>> getAllVendas() {
                return ResponseEntity.ok(vendaService.findAll());
        }

        @Operation(summary = "Obter uma venda pelo ID", description = "Retorna uma venda específica pelo seu ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venda encontrada", content = @Content(schema = @Schema(implementation = Venda.class))),
                        @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
        @GetMapping("/{id}")
        public ResponseEntity<Venda> getVendaById(@PathVariable Integer id) {
                return vendaService.findById(id)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        @Operation(summary = "Cria uma nova venda", description = "Cria uma nova venda e a salva no banco de dados")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Venda criada com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
        @PostMapping
        public ResponseEntity<Venda> createVenda(@Valid @RequestBody VendaForm vendaForm) {
                Venda novaVenda = vendaService.save(vendaForm);

                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(novaVenda.getCodvenda())
                                .toUri();

                return ResponseEntity.created(location).body(novaVenda);
        }

        @Operation(summary = "Atualiza uma venda", description = "Atualiza uma venda existente com base no seu ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venda atualizada com sucesso"),
                        @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
        @PutMapping("/{id}")
        public ResponseEntity<Venda> updateVenda(@PathVariable Integer id, @Valid @RequestBody VendaForm vendaForm) {
                Venda vendaAtualizada = vendaService.update(id, vendaForm);
                return ResponseEntity.ok(vendaAtualizada);
        }

        @Operation(summary = "Deleta uma venda", description = "Remove uma venda do banco de dados pelo seu ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venda apagada com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVenda(@PathVariable Integer id) {
                vendaService.deleteById(id);
                return ResponseEntity.noContent().build();
        }

}
