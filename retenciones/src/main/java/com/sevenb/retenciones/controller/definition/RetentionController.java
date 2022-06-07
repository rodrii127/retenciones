package com.sevenb.retenciones.controller.definition;

import com.sevenb.retenciones.dto.RetentionInputDto;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.SearchDate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.List;

public interface RetentionController {

    @Operation(summary = "Get all retention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful"),
            @ApiResponse(responseCode = "400", description = "The request contains missing or invalid information"),
            @ApiResponse(responseCode = "401", description = "The request is not validly authenticated"),
            @ApiResponse(responseCode = "403", description = "The client is not authorized for using this operation"),
            @ApiResponse(responseCode = "500", description = "There was an error during the execution of the service"),
            @ApiResponse(responseCode = "503", description = "Service not available"),
    })
    ResponseEntity<?> findAllRetention(String startDate,String endDate, Long idRetentionType);

    @Parameter(name = "id", description = "The retention id", required = true)
    ResponseEntity<?> findOneRetention(Long id);


    @Parameter(name = "searchDate", description = "Generate a retention file between 2 dates", required = true)
    ResponseEntity<?> retentionMunicipalityCsv(String startDate,String endDate,Long idRetentionType) throws Exception;

}
