package com.feliciano.store.resources.openapi;

import com.feliciano.store.dto.OrderDto;
import com.feliciano.store.resources.domain.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name = "Orders", description = "Order management endpoints")
public interface OrderResourceOpenApi {

    @Operation(summary = "Find order by ID", description = "Returns a single order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    ResponseEntity<OrderDto> find(
            @Parameter(description = "ID of the order to retrieve", required = true) Integer id
    );

    @Operation(summary = "Create a new order", description = "Creates a new order for the authenticated client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<Void> insert(
            @Parameter(description = "Order data", required = true) Order obj
    );

    @Operation(summary = "List orders with pagination", description = "Returns a paginated list of orders for the authenticated client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<Page<OrderDto>> findPage(
            @Parameter(description = "Page number (0-indexed)") Integer page,
            @Parameter(description = "Number of items per page") Integer linesPerPage,
            @Parameter(description = "Field to sort by") String orderBy,
            @Parameter(description = "Sort direction (ASC or DESC)") String direction
    );
}

