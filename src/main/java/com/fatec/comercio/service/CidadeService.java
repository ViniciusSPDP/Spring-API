package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Cidade;
import com.fatec.comercio.repository.CidadeRepository;

@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> findById(Integer id){
        return cidadeRepository.findById(id);
    }
    
    public Cidade save(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public void deleteById(Integer id){
        cidadeRepository.deleteById(id);
    }

    public Cidade update(Integer id, Cidade cidadeDetails){
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada com id: " + id));
        cidade.setNomecidade(cidadeDetails.getNomecidade());
        cidade.setUf(cidadeDetails.getUf());
        return cidadeRepository.save(cidade);
    }


}
