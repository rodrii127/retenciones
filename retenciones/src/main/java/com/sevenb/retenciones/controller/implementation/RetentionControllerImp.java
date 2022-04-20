package com.sevenb.retenciones.controller.implementation;

import com.sevenb.retenciones.controller.definition.RetentionController;
import com.sevenb.retenciones.service.definition.RetentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/retentions", produces = "application/json")
public class RetentionControllerImp implements RetentionController {

    private final RetentionService retentionService;

    @Autowired
    public RetentionControllerImp(RetentionService retentionService) {
        this.retentionService = retentionService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> createRetention(@RequestBody List<Long> idInvoices) {
              return retentionService.saveRetention(idInvoices);
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
        ByteArrayInputStream bis = retentionService.retentionPdf(id);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=retentionPdf.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
