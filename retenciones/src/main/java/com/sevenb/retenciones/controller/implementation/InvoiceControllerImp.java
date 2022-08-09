package com.sevenb.retenciones.controller.implementation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.dto.SearchInvoiceInputDto;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.service.definition.InvoiceService;

@RestController
@RequestMapping(value = "/invoice", produces = "application/json")
class InvoiceControllerImp {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> findByFilters(@RequestParam String startDate, @RequestParam String endDate,
                                       @RequestParam(required = false) Boolean impacted,
                                       @RequestParam(required = false) Long idProvider,
                                       @RequestHeader("Authorization") String bearerToken) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return invoiceService.findByFilters(new SearchInvoiceInputDto(LocalDate.parse(startDate, formatter),
            LocalDate.parse(endDate, formatter), impacted, idProvider), bearerToken);
    }

    @GetMapping("/{id}")
    public Invoice findInvoiceId(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> save(@RequestBody InvoiceDto invoiceDto, @RequestHeader("Authorization") String bearerToken) {
        return invoiceService.save(invoiceDto, bearerToken);
    }

    @PutMapping("/{id}")
    public Invoice update(@RequestBody Invoice invoice, @PathVariable Long id) {
        return invoiceService.update(invoice, id);
    }
}
