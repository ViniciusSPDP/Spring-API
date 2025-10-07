package com.fatec.comercio.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fatec.comercio.repository.UfRepository;
import com.fatec.comercio.models.Uf;
import java.util.List;

@Service
public class UfService {

    @Autowired
    private UfRepository ufRepository;

    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    //Buscar todos os UF
    public List<Uf> allUfs() {
        return ufRepository.findAll();
    }

    //Buscar UF por id
    public Uf ufId(Integer id) {
        return ufRepository.findByCoduf(id);
    }

    //Apagar UF por id
    public void deleteUf(Integer id) {
        ufRepository.deleteById(id);
    }

    //Salvar UF (CORRIGIDO)
    public Uf salvarUf(Uf uf) {
        return ufRepository.save(uf);
    }

    //Editar UF
    public void editarUf(Integer id, Uf uf) {
        uf.setCoduf(id);
        ufRepository.save(uf);
    }   
}