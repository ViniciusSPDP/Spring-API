package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Produto;
import com.fatec.comercio.models.Venda;
import com.fatec.comercio.models.VendaProduto;
import com.fatec.comercio.repository.ProdutoRepository;
import com.fatec.comercio.repository.VendaRepository;
import jakarta.transaction.Transactional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository; // Precisamos do repositório de produto

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(Integer id) {
        return vendaRepository.findById(id);
    }

    @Transactional
    public Venda save(Venda venda) {
        // Garante que a relação bidirecional seja estabelecida
        venda.getProdutos().forEach(item -> item.setVenda(venda));

        // ----- INÍCIO DA CORREÇÃO -----
        // Para cada item da venda, substituímos o produto "detached" por um "managed"
        Set<VendaProduto> managedItems = venda.getProdutos().stream()
            .map(item -> {
                Produto detachedProduto = item.getProduto();
                // Busca a versão mais recente do produto no banco de dados
                Produto managedProduto = produtoRepository.findById(detachedProduto.getCodproduto())
                    .orElseThrow(() -> new RuntimeException("Produto com ID " + detachedProduto.getCodproduto() + " não encontrado."));
                
                // Atualiza o item da venda para usar a entidade gerenciada
                item.setProduto(managedProduto);
                return item;
            })
            .collect(Collectors.toSet());
        
        venda.setProdutos(managedItems);
        // ----- FIM DA CORREÇÃO -----

        return vendaRepository.save(venda);
    }

    public void deleteById(Integer id) {
        vendaRepository.deleteById(id);
    }
}