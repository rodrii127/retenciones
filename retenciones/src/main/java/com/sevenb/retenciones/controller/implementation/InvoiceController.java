package com.sevenb.retenciones.controller.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.SearchDate;
import com.sevenb.retenciones.service.definition.InvoiceService;

@RestController
@RequestMapping(value = "/invoice", produces = "application/json")
class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(produces = "application/json")
    public Invoice save(@RequestBody Invoice invoice) {
        return invoiceService.save(invoice);
    }

    @GetMapping
    public List<Invoice> invoice() {
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public Invoice findInvoiceId(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    @PutMapping("/{id}")
    public Invoice update(@RequestBody Invoice invoice, @PathVariable Long id) {
        return invoiceService.update(invoice, id);
    }

    @GetMapping("/date")
    public List<Invoice> findByDateBetween(@RequestBody SearchDate searchDate) {
        return invoiceService.findByDateBetween(searchDate.getStartDate(), searchDate.getEndDate());
    }
}
