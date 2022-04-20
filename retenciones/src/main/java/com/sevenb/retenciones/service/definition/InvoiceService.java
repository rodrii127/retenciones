package com.sevenb.retenciones.service.definition;

import com.sevenb.retenciones.entity.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface InvoiceService {
      Invoice save(Invoice invoice);
      List<Invoice> findAll();
      Invoice findById(Long id);
      Invoice update(Invoice invoice, Long id);

    Optional<Invoice> findByDateBetween(LocalDate date1 , LocalDate date2);

}
