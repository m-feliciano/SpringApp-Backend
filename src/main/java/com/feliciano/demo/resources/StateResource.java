package com.feliciano.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feliciano.demo.dto.CityDTO;
import com.feliciano.demo.dto.StateDTO;
import com.feliciano.demo.resources.domain.City;
import com.feliciano.demo.resources.domain.State;
import com.feliciano.demo.services.CityService;
import com.feliciano.demo.services.StateService;

@RestController
@RequestMapping(value = "/api/v1/states")
public class StateResource {

	@Autowired
	private StateService service;

	@Autowired
	private CityService cityService;

	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<List<StateDTO>> findAll() {
		List<State> obj = service.findAll();
		List<StateDTO> listDto = obj.stream().map(StateDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
	private ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
		List<City> obj = cityService.findByState(stateId);
		List<CityDTO> listDto = obj.stream().map(CityDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
