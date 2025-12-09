package com.feliciano.store.resources.openapi;

import com.feliciano.store.dto.CategoryDTO;
import com.feliciano.store.resources.domain.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Categories", description = "Category management endpoints")
public interface CategoryResourceOpenApi {

    @Operation(summary = "Find category by ID", description = "Returns a single category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    ResponseEntity<Category> find(
            @Parameter(description = "ID of the category to retrieve", required = true) Integer id
    );

    @Operation(summary = "Create a new category", description = "Creates a new category (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<Void> insert(
            @Parameter(description = "Category data", required = true) CategoryDTO objDto
    );

    @Operation(summary = "Update a category", description = "Updates an existing category (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    ResponseEntity<Void> update(
            @Parameter(description = "Category data", required = true) CategoryDTO objDto,
            @Parameter(description = "ID of the category to update", required = true) Integer id
    );

    @Operation(summary = "Delete a category", description = "Deletes a category by ID (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation")
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "ID of the category to delete", required = true) Integer id
    );

    @Operation(summary = "List all categories", description = "Returns a list of all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully")
    })
    ResponseEntity<List<CategoryDTO>> findAll();

    @Operation(summary = "List categories with pagination", description = "Returns a paginated list of categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully")
    })
    ResponseEntity<Page<CategoryDTO>> findPage(
            @Parameter(description = "Page number (0-indexed)") Integer page,
            @Parameter(description = "Number of items per page") Integer linesPerPage,
            @Parameter(description = "Field to sort by") String orderBy,
            @Parameter(description = "Sort direction (ASC or DESC)") String direction
    );
}

