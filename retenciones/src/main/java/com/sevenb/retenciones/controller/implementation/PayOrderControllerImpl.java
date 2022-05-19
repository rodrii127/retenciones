package com.sevenb.retenciones.controller.implementation;

import com.sevenb.retenciones.controller.definition.PayOrderController;
import com.sevenb.retenciones.dto.PayOrderInputDto;
import com.sevenb.retenciones.service.definition.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

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
        return payOrderService.savePayOrder(payOrderInputDto.getIdPayOder(), payOrderInputDto.getStartDate());
    }

    @Override
    public ResponseEntity<?> findOnePayOrder(Long id) {
        return null;
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
