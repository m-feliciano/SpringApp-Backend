package com.feliciano.store.resources.openapi;

import com.feliciano.store.dto.EmailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication", description = "Authentication and password recovery endpoints")
public interface AuthResourceOpenApi {

    @Operation(
            summary = "Refresh authentication token",
            description = "Generates a new JWT token for an authenticated user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Token refreshed successfully. New token is returned in the Authorization header"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or expired token"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    ResponseEntity<Void> refreshToken(
            @Parameter(hidden = true) HttpServletResponse response
    );

    @Operation(
            summary = "Request password reset",
            description = "Sends a new temporary password to the user's email address"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Password reset email sent successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid email format"),
            @ApiResponse(responseCode = "404", description = "Email not found")
    })
    ResponseEntity<Void> forgot(
            @Parameter(
                    description = "Email address to send the new password",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EmailDTO.class))
            )
            EmailDTO objDto
    );
}

