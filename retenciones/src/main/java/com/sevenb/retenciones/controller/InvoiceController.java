package com.sevenb.retenciones.controller;

import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/invoice", produces = "application/json")
class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(produces = "application/json")
    public Invoice save(@RequestBody Invoice invoice){
        return invoiceService.save(invoice);
    }

    @GetMapping
    public List<Invoice> invoice(){
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public Invoice findInvoiceId(@PathVariable Long id){
                return invoiceService.findById(id);
    }

    @PutMapping("/{id}")
    public Invoice update(@RequestBody Invoice invoice, @PathVariable Long id ) {


        return invoiceService.update( invoice,id);

    }


}
