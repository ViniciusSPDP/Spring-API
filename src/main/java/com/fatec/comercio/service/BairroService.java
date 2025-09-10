package com.fatec.comercio.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fatec.comercio.repository.BairroRepository;
import com.fatec.comercio.models.Bairro;
import java.util.List;

@Service
public class BairroService {

    @Autowired
    private final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    public List<Bairro> allBairros() {
        return bairroRepository.findAll();
    }

    public Bairro bairroId(Integer id) {
        return bairroRepository.findByCodbairro(id);
    }

    public String apagaId(Integer id) {
        return "Bairro com id " + id + " apagado com sucesso!";
    }

    public Bairro salvarBairro(Bairro bairro) {
        return bairroRepository.save(bairro);
    }

    public void editarBairro(Integer id, Bairro bairro) {
        bairro.setCodbairro(id);
        bairroRepository.save(bairro);
    }
}
