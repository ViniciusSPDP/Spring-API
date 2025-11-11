package com.fatec.comercio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class VendaProdutoForm {
    
    @NotNull(message = "O ID Do produto é obrigatório")
    private Integer produtoId;

    @NotNull(message = "A quantidade vendida é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    private Integer quantidadev;

    @NotNull(message = "O valor unitário é obrigatório")
    @Min(value = 0, message = "O valor unitário não pode ser negativo")
    private Double valorv;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidadev() {
        return quantidadev;
    }

    public void setQuantidadev(Integer quantidadev) {
        this.quantidadev = quantidadev;
    }

    public Double getValorv() {
        return valorv;
    }

    public void setValorv(Double valorv) {
        this.valorv = valorv;
    }

    

}
