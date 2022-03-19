package com.sevenb.retenciones.controller;

import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping
    public Provider save(@RequestBody Provider provider){
        return providerService.save(provider);
    }

    @GetMapping
    public List<Provider> provider(){return providerService.findAll();}

    @GetMapping("/{id}")
    public Provider findProviderId(@PathVariable Long id){
        return providerService.findById(id);
    }

    @PutMapping("/{id}")
    public Provider update(@RequestBody Provider provider, @PathVariable Long id ){
        return providerService.update(provider,id);

    }

}
