package com.sevenb.retenciones.service.definition;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.Provider;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

public interface PayOrderService {
    public ResponseEntity<?> savePayOrder(List<Long> idRetentions, LocalDate startDate);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findOnePayOrder(Long id);

    public ByteArrayInputStream payOderPdf(Long id);

    public ResponseEntity<?> findByDateBetween(LocalDate startDate, LocalDate endDate);



}
