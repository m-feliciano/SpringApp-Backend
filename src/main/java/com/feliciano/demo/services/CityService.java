package com.feliciano.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliciano.demo.repositories.CityRepository;
import com.feliciano.demo.resources.domain.City;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;

	public List<City> findByState(Integer state_id) {
		return repo.findCities(state_id);
	}
}
