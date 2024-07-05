package com.sevenb.retenciones.service.definition;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PayOrderService {
    ResponseEntity<?> savePayOrder(List<Long> idRetentions, LocalDate startDate, String bearerToken);

    ResponseEntity<?> findAll(LocalDate startDate, LocalDate endDate, Long providerId, String bearerToken);

    ResponseEntity<?> findOnePayOrder(Long id);

    ByteArrayInputStream payOderPdf(Long id);

    ResponseEntity<?> findByDateBetween(LocalDate startDate, LocalDate endDate, String bearerToken);

    FileOutputStream createInfoByDateXls(LocalDate startDate, LocalDate endDate, String bearerToken);

    void deleteById(Long id, boolean logicalDelete, String bearerToken);
}