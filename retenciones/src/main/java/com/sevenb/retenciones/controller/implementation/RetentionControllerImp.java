package com.sevenb.retenciones.controller.implementation;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping
    public ResponseEntity<?> createRetention(@RequestBody RetentionInputDto inputDto) {
        return null;//retentionService.saveRetention(inputDto);
    }

    @Override
    public ResponseEntity<?> findAllRetention() {
        return null;
    }

    @Override
    public ResponseEntity<?> findOneRetention(Long id) {
        return null;
    }

    @Override
    @GetMapping(value = "/retentionPdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> retentionPdf(@PathVariable Long id) {
    /*    ByteArrayInputStream bis = retentionService.retentionPdf(id);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=retentionPdf.pdf");
*/
        return null;/*ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));*/
    }

    @Override
    @GetMapping(value = "/retentionCsvMunicipality", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> retentionMunicipalityCsv(@RequestBody SearchDate searchDate) throws Exception {
        File file = retentionService.generaFileMunicipality(searchDate);
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
