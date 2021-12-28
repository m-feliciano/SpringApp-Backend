package com.feliciano.demo.services;

import com.feliciano.demo.repositories.CidadeRepository;
import com.feliciano.demo.resources.domain.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findByEstado(Integer estado_id) {
        return repo.findCidades(estado_id);
    }
}
