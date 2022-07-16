package com.sevenb.retenciones.service.definition;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PayOrderService {
    public ResponseEntity<?> savePayOrder(List<Long> idRetentions, LocalDate startDate, String bearerToken);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findOnePayOrder(Long id);

    public ByteArrayInputStream payOderPdf(Long id);

    public ResponseEntity<?> findByDateBetween(LocalDate startDate, LocalDate endDate, String bearerToken);

}
