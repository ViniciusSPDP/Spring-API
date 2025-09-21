package com.fatec.comercio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Rua;
import com.fatec.comercio.repository.RuaRepository;

@Service
public class RuaService {
    
    private final RuaRepository ruaRepository;
    
    public RuaService(RuaRepository ruaRepository) {
        this.ruaRepository = ruaRepository;
    }

    public List<Rua> allRuas() {
        return ruaRepository.findAll();
    }

    public Rua ruaId(Integer id) {
        return ruaRepository.findByCodrua(id);
    }

    public String apagaId(Integer id) {
        ruaRepository.deleteById(id);
        return "Rua com id " + id + " apagada com sucesso!";
    }

    public Rua salvarRua(Rua rua) {
        return ruaRepository.save(rua);
    }

    public void editarRua(Integer id, Rua rua) {
        rua.setCodrua(id);
        ruaRepository.save(rua);
    }

    
}
