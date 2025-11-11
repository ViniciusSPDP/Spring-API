package com.fatec.comercio.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.dto.VendaForm;
import com.fatec.comercio.exception.ResourceNotFoundException;
import com.fatec.comercio.models.Cliente;
import com.fatec.comercio.models.Produto;
import com.fatec.comercio.models.Venda;
import com.fatec.comercio.models.VendaProduto;
import com.fatec.comercio.repository.ClienteRepository;
import com.fatec.comercio.repository.ProdutoRepository;
import com.fatec.comercio.repository.VendaRepository;
import jakarta.transaction.Transactional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(Integer id) {
        return vendaRepository.findById(id);
    }

    @Transactional
    public Venda save(VendaForm vendaForm) {

        Cliente cliente = clienteRepository.findById(vendaForm.getClienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + vendaForm.getClienteId()));
        
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDatavenda(new Date());

        Set<VendaProduto> itensDaVenda = vendaForm.getProdutos().stream().map(itemForm -> {
            Produto produto = produtoRepository.findById(itemForm.getProdutoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + itemForm.getProdutoId()));

            if (produto.getQuantidade() < itemForm.getQuantidadev()) {
                throw new IllegalArgumentException("Quantidade insuficiente em estoque para o produto ID: " + produto.getNomeproduto());
            }

            produto.setQuantidade(produto.getQuantidade() - itemForm.getQuantidadev());
            produtoRepository.save(produto);

            VendaProduto item = new VendaProduto();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantv(itemForm.getQuantidadev());
            item.setValorv(itemForm.getValorv());

            return item;
        })
        .collect(Collectors.toSet());
        venda.setProdutos(itensDaVenda);
        return vendaRepository.save(venda);
    }

    @Transactional
    public void deleteById(Integer id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));

        venda.getProdutos().forEach(vp -> {
            Produto produto = vp.getProduto();
            produto.setQuantidade(produto.getQuantidade() + vp.getQuantv());
            produtoRepository.save(produto);
        });

        vendaRepository.delete(venda);

    }
}