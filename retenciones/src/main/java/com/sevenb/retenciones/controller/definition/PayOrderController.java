package com.sevenb.retenciones.controller.definition;

import com.sevenb.retenciones.dto.PayOrderInputDto;
import com.sevenb.retenciones.entity.Provider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PayOrderController {
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
    ResponseEntity<?> createPayOrder(PayOrderInputDto payOrderInputDto);

    @Parameter(name = "id", description = "The pay order id", required = true)
    ResponseEntity<?> payOrderPdf(Long id);
}
