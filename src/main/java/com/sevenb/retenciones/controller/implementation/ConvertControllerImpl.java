package com.sevenb.retenciones.controller.implementation;

import com.sevenb.retenciones.controller.definition.ConvertController;
import com.sevenb.retenciones.dto.ConvertInputDto;
import com.sevenb.retenciones.service.definition.RetentionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
@RestController
@RequestMapping(value = "/convert", produces = "application/json")
public class ConvertControllerImpl implements ConvertController {

    private final RetentionService retentionService;

    public ConvertControllerImpl(RetentionService retentionService) {
        this.retentionService = retentionService;
    }

    @Override
    @PostMapping(value = "/atm")
    public ResponseEntity<?> convertInputAtm(@RequestBody List<ConvertInputDto> convertInputList) throws Exception {
        File file = retentionService.convertRetentionAtmCsv(convertInputList);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
