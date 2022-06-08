package com.sevenb.retenciones.controller.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sevenb.retenciones.controller.definition.RetentionController;
import com.sevenb.retenciones.dto.RetentionInputDto;
import com.sevenb.retenciones.entity.SearchDate;
import com.sevenb.retenciones.security.JWTValidator;
import com.sevenb.retenciones.service.definition.RetentionService;

@RestController
@RequestMapping(value = "/retentions", produces = "application/json")
public class RetentionControllerImp implements RetentionController {

    private final RetentionService retentionService;

    private JWTValidator validator;

    @Autowired
    public RetentionControllerImp(RetentionService retentionService) {
        this.retentionService = retentionService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> findAllRetention(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long idRetentionType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return retentionService.findAll(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), idRetentionType);
    }

    @Override
    public ResponseEntity<?> findOneRetention(Long id) {
        return null;
    }

    @Override
    @GetMapping(value = "/retentionCsvMunicipality", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> retentionMunicipalityCsv(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long idRetentionType) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        File file = retentionService.generaFileMunicipality(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter),idRetentionType);
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
