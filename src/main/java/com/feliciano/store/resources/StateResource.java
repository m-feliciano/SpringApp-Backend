package com.feliciano.store.resources;

import com.feliciano.store.dto.CityDTO;
import com.feliciano.store.dto.StateDTO;
import com.feliciano.store.resources.domain.City;
import com.feliciano.store.resources.domain.State;
import com.feliciano.store.resources.openapi.StateResourceOpenApi;
import com.feliciano.store.services.CityService;
import com.feliciano.store.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/states")
public class StateResource implements StateResourceOpenApi {
	@Autowired
	private StateService stateService;
	@Autowired
	private CityService cityService;

	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll() {
		List<State> obj = stateService.findAll();
		List<StateDTO> listDto = obj.stream().map(StateDTO::new).toList();
        return ResponseEntity.ok(listDto);
	}

	@RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
		List<City> obj = cityService.findByState(stateId);
		List<CityDTO> listDto = obj.stream().map(CityDTO::new).toList();
        return ResponseEntity.ok(listDto);
	}

}
