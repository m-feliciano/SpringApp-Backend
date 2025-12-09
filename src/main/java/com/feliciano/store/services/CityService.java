package com.feliciano.store.services;

import com.feliciano.store.repositories.CityRepository;
import com.feliciano.store.resources.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository repository;

    @Autowired
    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public List<City> findByState(Integer state_id) {
        return repository.findCities(state_id);
	}
}
