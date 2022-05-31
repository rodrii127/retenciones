package com.sevenb.retenciones.controller.implementation;

import java.io.ByteArrayInputStream;

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
    public ResponseEntity<?> createPayOrder(@RequestBody PayOrderInputDto payOrderInputDto) {
        return payOrderService.savePayOrder(payOrderInputDto.getIdInvoices(), payOrderInputDto.getStartDate());
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

}
