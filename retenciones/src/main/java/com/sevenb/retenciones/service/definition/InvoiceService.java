package com.sevenb.retenciones.service.definition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sevenb.retenciones.entity.Invoice;


public interface InvoiceService {
    Invoice save(Invoice invoice);

    List<Invoice> findAll();

    Invoice findById(Long id);

    Invoice update(Invoice invoice, Long id);

    List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
