package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Marca;
import com.fatec.comercio.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> allMarcas() {
        return marcaRepository.findAll();
    }

    public Marca marcaId(Integer id) {
        return marcaRepository.findByCodmarca(id);
    }

    public String apagaId(Integer id) {
        marcaRepository.deleteById(id);
        return "Marca com id " + id + " apagado com sucesso!";
    }

    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void editarMarca(Integer id, Marca marca) {
        marca.setCodmarca(id);
        marcaRepository.save(marca);
    }
    
}
