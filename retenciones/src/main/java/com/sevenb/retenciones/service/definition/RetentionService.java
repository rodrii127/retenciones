package com.sevenb.retenciones.service.definition;

import com.sevenb.retenciones.entity.Retention;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface RetentionService {

   public ResponseEntity<?> saveRetention(List<Long> invoices);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findOneRetention(Long id);

    public ByteArrayInputStream retentionPdf(Long id);

}
