package com.sevenb.retenciones.service.definition;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.dto.SearchInvoiceInputDto;
import com.sevenb.retenciones.entity.Invoice;

public interface InvoiceService {
    ResponseEntity<?> save(InvoiceDto invoiceDto, String bearerToken);

    List<Invoice> findAll();

    Invoice findById(Long id);

    Invoice update(InvoiceDto invoiceDto, Long id);

    List<Invoice> findByFilters(SearchInvoiceInputDto inputDto, String bearerToken);

}
