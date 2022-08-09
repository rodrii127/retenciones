package com.sevenb.retenciones.service.definition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.dto.SearchInvoiceInputDto;
import com.sevenb.retenciones.entity.Invoice;
import org.springframework.http.ResponseEntity;


public interface InvoiceService {
    ResponseEntity<?> save(InvoiceDto invoiceDto, String bearerToken);

    List<Invoice> findAll();

    Invoice findById(Long id);

    Invoice update(InvoiceDto invoiceDto, Long id);

    List<Invoice> findByFilters(SearchInvoiceInputDto inputDto, String bearerToken);

}
