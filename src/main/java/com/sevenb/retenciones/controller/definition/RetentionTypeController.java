package com.sevenb.retenciones.controller.definition;

import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface RetentionTypeController {

    @Operation(summary = "Get all Retention Type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request successful"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    ResponseEntity<?> getRetentionType();
}
