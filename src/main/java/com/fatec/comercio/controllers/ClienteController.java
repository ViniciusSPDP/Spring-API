package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Cliente;
import com.fatec.comercio.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createCliente(@RequestBody Cliente cliente) {
        clienteService.save(cliente);
        return "Cliente salvo com sucesso!";
    }

    @PutMapping("/{id}")
    public String updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetails) {
        clienteDetails.setCodcliente(id);
        clienteService.save(clienteDetails);
        return "Cliente com id " + id + " editado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return "Cliente com id " + id + " apagado com sucesso!";
    }
}