package com.feliciano.store.resources.openapi;

import com.feliciano.store.dto.ClientDTO;
import com.feliciano.store.dto.ClientNewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Clients", description = "Client management endpoints")
public interface ClientResourceOpenApi {

    @Operation(summary = "Find client by ID", description = "Returns a single client by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    ResponseEntity<?> find(
            @Parameter(description = "ID of the client to retrieve", required = true) Integer id
    );

    @Operation(summary = "Find client by email", description = "Returns a single client by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    ResponseEntity<?> find(
            @Parameter(description = "Email of the client to retrieve", required = true) String email
    );

    @Operation(summary = "Create a new client", description = "Registers a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    ResponseEntity<Void> insert(
            @Parameter(description = "Client registration data", required = true) ClientNewDTO objDto
    );

    @Operation(summary = "Update a client", description = "Updates an existing client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    ResponseEntity<Void> update(
            @Parameter(description = "Client data", required = true) ClientDTO objDto,
            @Parameter(description = "ID of the client to update", required = true) Integer id
    );

    @Operation(summary = "Delete a client", description = "Deletes a client by ID (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "409", description = "Cannot delete client with associated orders")
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "ID of the client to delete", required = true) Integer id
    );

    @Operation(summary = "List all clients", description = "Returns a list of all clients (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<List<ClientDTO>> find();

    @Operation(summary = "List clients with pagination", description = "Returns a paginated list of clients (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<Page<ClientDTO>> findPage(
            @Parameter(description = "Page number (0-indexed)") Integer page,
            @Parameter(description = "Number of items per page") Integer linesPerPage,
            @Parameter(description = "Field to sort by") String orderBy,
            @Parameter(description = "Sort direction (ASC or DESC)") String direction
    );

    @Operation(summary = "Upload profile picture", description = "Uploads a profile picture for the authenticated client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Picture uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid file"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<Void> uploadProfilePicture(
            @Parameter(description = "Image file", required = true) MultipartFile file
    );
}

