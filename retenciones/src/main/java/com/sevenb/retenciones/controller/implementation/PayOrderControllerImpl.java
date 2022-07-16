package com.sevenb.retenciones.controller.implementation;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.sevenb.retenciones.dto.SearchInvoiceInputDto;
import com.sevenb.retenciones.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sevenb.retenciones.controller.definition.PayOrderController;
import com.sevenb.retenciones.dto.PayOrderInputDto;
import com.sevenb.retenciones.service.definition.PayOrderService;

@RestController
@RequestMapping(value = "/pay-order", produces = "application/json")
public class PayOrderControllerImpl implements PayOrderController {

    private final PayOrderService payOrderService;

    @Autowired
    public PayOrderControllerImpl(PayOrderService payOrderService) {
        this.payOrderService = payOrderService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> createPayOrder(@RequestBody PayOrderInputDto payOrderInputDto, @RequestHeader("Authorization") String bearerToken) {
        return payOrderService.savePayOrder(payOrderInputDto.getIdInvoices(), payOrderInputDto.getStartDate(), bearerToken);
    }
    @Override
    @GetMapping(value = "/payOrderPdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> payOrderPdf(@PathVariable Long id) {
        ByteArrayInputStream bis = payOrderService.payOderPdf(id);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=retentionPdf.pdf");
        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }
    @Override
    @GetMapping
    public ResponseEntity<?> findByDateBetween(@RequestParam String startDate, @RequestParam String endDate,
                                               @RequestHeader("Authorization") String bearerToken) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return payOrderService.findByDateBetween(LocalDate.parse(startDate, formatter),
            LocalDate.parse(endDate, formatter), bearerToken);
    }

}
