package com.sevenb.retenciones.controller.implementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sevenb.retenciones.controller.definition.ProviderController;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.service.definition.ProviderService;

/**
 * Provider controller. Defines path and parameters required for CRUD operations on provider entity.
 */
@RestController
@RequestMapping(value = "/providers", produces = "application/json")
public class ProviderControllerImp implements ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderControllerImp(ProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> createProvider(@RequestBody @Valid Provider provider) {
        return providerService.saveProvider(provider);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> findAllProviders() {
        return providerService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findOneProvider(@PathVariable Long id) {
        return providerService.findOneProvider(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProvider(@PathVariable Long id, @RequestBody @Valid Provider providerNew) {
        return providerService.update(id, providerNew);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProvider(@PathVariable Long id) {
        return providerService.delete(id);
    }
}
