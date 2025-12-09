package com.feliciano.store.resources.openapi;

import com.feliciano.store.dto.ProductDTO;
import com.feliciano.store.resources.domain.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name = "Products", description = "Product management endpoints")
public interface ProductResourceOpenApi {

    @Operation(summary = "Find product by ID", description = "Returns a single product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    ResponseEntity<Product> find(
            @Parameter(description = "ID of the product to retrieve", required = true) Integer id
    );

    @Operation(summary = "Search products", description = "Returns a paginated list of products filtered by name and categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    })
    ResponseEntity<Page<ProductDTO>> findPage(
            @Parameter(description = "Product name filter")
            String name,
            @Parameter(description = "Comma-separated category IDs")
            String categories,
            @Parameter(description = "Page number (0-indexed)")
            Integer page,
            @Parameter(description = "Number of items per page")
            Integer linesPerPage,
            @Parameter(description = "Field to sort by")
            String orderBy,
            @Parameter(description = "Sort direction (ASC or DESC)")
            String direction
    );
}

