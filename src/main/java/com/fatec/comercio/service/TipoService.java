package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Tipo;
import com.fatec.comercio.repository.TipoRepository;

@Service
public class TipoService {

    @Autowired
    private final TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public List<Tipo> allTipos() {
        return tipoRepository.findAll();
    }

    public Tipo tipoId(Integer id) {
        return tipoRepository.findByCodtipo(id);
    }

    public String apagaId(Integer id) {
        tipoRepository.deleteById(id);
        return "Tipo com id " + id + " apagado com sucesso!";
    }

    public Tipo salvarTipo(Tipo tipo) {
        return tipoRepository.save(tipo);
    }

    public void editarTipo(Integer id, Tipo tipo) {
        tipo.setCodtipo(id);
        tipoRepository.save(tipo);
    }
    
}
