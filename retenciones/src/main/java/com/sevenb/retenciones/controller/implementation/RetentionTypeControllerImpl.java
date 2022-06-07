package com.sevenb.retenciones.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sevenb.retenciones.controller.definition.RetentionTypeController;
import com.sevenb.retenciones.service.definition.RetentionTypeService;

@RestController
@RequestMapping(value = "/retention-type", produces = "application/json")
public class RetentionTypeControllerImpl implements RetentionTypeController {

    private final RetentionTypeService retentionTypeService;

    @Autowired
    public RetentionTypeControllerImpl(RetentionTypeService retentionTypeService) {
        this.retentionTypeService = retentionTypeService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getRetentionType() {
        return retentionTypeService.getRetentionType();
    }
}
