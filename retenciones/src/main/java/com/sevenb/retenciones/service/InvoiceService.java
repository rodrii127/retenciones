package com.sevenb.retenciones.service;

import com.sevenb.retenciones.entity.Invoice;

import java.util.List;


public interface InvoiceService {
      Invoice save(Invoice invoice);

      List<Invoice> findAll();
   Invoice findById(Long id);
   Invoice update(Invoice invoice, Long id);

}
