package com.fatec.comercio.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VendaForm {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Integer clienteId;

    @Valid
    @Size(min = 1, message = "A venda deve conter pelo menos um produto")
    private List<VendaProdutoForm> produtos;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<VendaProdutoForm> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<VendaProdutoForm> produtos) {
        this.produtos = produtos;
    }

    
    
}
