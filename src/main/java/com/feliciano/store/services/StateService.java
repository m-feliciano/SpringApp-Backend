package com.feliciano.store.services;

import com.feliciano.store.repositories.StateRepository;
import com.feliciano.store.resources.domain.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private final StateRepository repository;

    @Autowired
    public StateService(StateRepository repository) {
        this.repository = repository;
    }

    public List<State> findAll() {
        return repository.findAllByOrderByName();
	}
}
