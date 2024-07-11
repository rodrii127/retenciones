package com.sevenb.retenciones.controller.implementation;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sevenb.retenciones.controller.definition.PayOrderController;
import com.sevenb.retenciones.dto.PayOrderInputDto;
import com.sevenb.retenciones.service.definition.PayOrderService;

@RestController
@RequestMapping(value = "/pay-order", produces = "application/json")
public class PayOrderControllerImpl implements PayOrderController {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final PayOrderService payOrderService;

    @Autowired
    public PayOrderControllerImpl(PayOrderService payOrderService) {
        this.payOrderService = payOrderService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
                                     @RequestParam(required = false) Long providerId, @RequestHeader("Authorization") String bearerToken) {
        return payOrderService.findAll(parseDate(startDate), parseDate(endDate), providerId, bearerToken);
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
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }

    @Override
    @GetMapping(value = "/payOrderList")
    public ResponseEntity<?> findByDateBetween(@RequestParam String startDate, @RequestParam String endDate,
                                               @RequestHeader("Authorization") String bearerToken) {
        return payOrderService.findByDateBetween(LocalDate.parse(startDate, DATE_TIME_FORMATTER),
            LocalDate.parse(endDate, DATE_TIME_FORMATTER), bearerToken);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id, @RequestParam(defaultValue = "true") boolean logicalDelete,
                                        @RequestHeader("Authorization") String bearerToken) {
        payOrderService.deleteById(id, logicalDelete, bearerToken);
        return ResponseEntity.noContent().build();
    }

    private LocalDate parseDate(String date) {
        return Optional.ofNullable(date).map((optDate) -> {
            return LocalDate.parse(optDate, DATE_TIME_FORMATTER);
        }).orElse((LocalDate) null);
    }
}