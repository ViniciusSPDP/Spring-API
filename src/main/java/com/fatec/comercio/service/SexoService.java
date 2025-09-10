package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.repository.SexoRepository;

@Service
public class SexoService {

//Injeção de Dependencia
    @Autowired
    private final SexoRepository sexoRepository;

    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<Sexo> allSexos() {
        return sexoRepository.findAll();
    }

    public Sexo sexoId(Integer id) {
        return sexoRepository.findByCodsexo(id);
    }

    public String apagaId(Integer id) {
        sexoRepository.deleteById(id);
        return "Sexo com id " + id + " apagado com sucesso!";
    }

    public Sexo salvarSexo(Sexo sexo) {

        return sexoRepository.save(sexo);

    }

//editar sexo
    public void editarSexo(Integer id, Sexo sexo) {
        sexo.setCodsexo(id);
        sexoRepository.save(sexo);

    }

}
