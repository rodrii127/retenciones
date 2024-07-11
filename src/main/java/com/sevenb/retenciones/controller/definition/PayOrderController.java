package com.sevenb.retenciones.controller.definition;

import org.springframework.http.ResponseEntity;
import com.sevenb.retenciones.dto.PayOrderInputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface PayOrderController {

    @Operation(summary = "Find all pay orders for a specific company and optional filters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request successful"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameters({
        @Parameter(name = "startDate", description = "Start date to search"),
        @Parameter(name = "endDate", description = "End date to search"),
        @Parameter(name = "providerId", description = "Provider id to search")
    })
    ResponseEntity<?> findAll(String startDate, String endDate, Long providerId, String bearerToken);

    @Operation(summary = "Create a Pay Order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request successful"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameters({
        @Parameter(name = "listRetentionId", description = "The pay order to be created", required = true),
        @Parameter(name = "startDate", description = "Date", required = true)
    })
    ResponseEntity<?> createPayOrder(PayOrderInputDto payOrderInputDto, String bearerToken);

    @Parameter(name = "id", description = "The pay order id", required = true)
    ResponseEntity<?> payOrderPdf(Long id);

    @Parameter(name = "startDate", description = "Start date", required = true)
    @Parameter(name = "endDate", description = "End date", required = true)
    ResponseEntity<?> findByDateBetween(String startDate, String endDate, String bearerToken);

    @Operation(summary = "Delete a pay order by id. It could be a logical or a physical delete")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pay order successfully deleted"),
        @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
        @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
        @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
        @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    @Parameters({
        @Parameter(name = "id", description = "The pay order id", required = true),
        @Parameter(name = "logicalDelete", description = "Boolean value that represents if the delete should be logical")
    })
    ResponseEntity<?> deleteById(Long id, boolean logicalDelete, String bearerToken);
}
