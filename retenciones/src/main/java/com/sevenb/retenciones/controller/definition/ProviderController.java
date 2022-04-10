package com.sevenb.retenciones.controller.definition;

import org.springframework.http.ResponseEntity;
import com.sevenb.retenciones.entity.Provider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Provider controller definition.
 */
public interface ProviderController {

    @Operation(summary = "Create a Provider")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request successful"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameters({
        @Parameter(name = "provider", description = "The provider to be created", required = true)
    })
    ResponseEntity<?> createProvider(Provider provider);

    @Operation(summary = "Get all providers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request successful"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    ResponseEntity<?> findAllProviders();

    @Operation(summary = "Find a specific provider")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request successful"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameter(name = "id", description = "The provider id", required = true)
    ResponseEntity<?> findOneProvider(Long id);

    @Operation(summary = "Updates one provider according to id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The resource has been successfully updated"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "404", description = "The resource was not found"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameter(name = "id", description = "Provider id", required = true)
    ResponseEntity<?> updateProvider(Long id, Provider providerNew);

    @Operation(summary = "Delete a provider according to id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "The resource has been successfully deleted"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "404", description = "The resource was not found"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameter(name = "id", description = "Provider id", required = true)
    ResponseEntity<?> deleteProvider(Long id);
}
