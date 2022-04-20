package com.sevenb.retenciones.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.service.definition.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice update(Invoice invoice, Long id) {
        Invoice invoiceCurrent = findById(id);

        invoiceCurrent.setNumber(invoice.getNumber());
        invoiceCurrent.setPointSale(invoice.getPointSale());
        invoiceCurrent.setProvider(invoice.getProvider());
        invoiceCurrent.setEngraved(invoice.getEngraved());
        invoiceCurrent.setExempt(invoice.getExempt());
        invoiceCurrent.setIva105(invoice.getIva105());
        invoiceCurrent.setIva21(invoice.getIva21());
        invoiceCurrent.setIibb(invoice.getIibb());
        invoiceCurrent.setTaxedOthers(invoice.getTaxedOthers());
        invoiceCurrent.setMunicipality(invoice.getMunicipality());
        invoiceCurrent.setImpacted(invoice.getImpacted());

        return invoiceRepository.save(invoiceCurrent);
    }

    @Override
    public List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findByDateBetween(startDate, endDate);
    }
}
