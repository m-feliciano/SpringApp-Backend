package com.feliciano.store.resources.openapi;

import com.feliciano.store.dto.CityDTO;
import com.feliciano.store.dto.StateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "States", description = "State and city management endpoints")
public interface StateResourceOpenApi {

    @Operation(summary = "List all states", description = "Returns a list of all Brazilian states")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "States retrieved successfully")
    })
    ResponseEntity<List<StateDTO>> findAll();

    @Operation(summary = "List cities by state", description = "Returns a list of cities for a specific state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cities retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "State not found")
    })
    ResponseEntity<List<CityDTO>> findCities(
            @Parameter(description = "ID of the state", required = true) Integer stateId
    );
}

