package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.service.definition.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {

        @Autowired
        private InvoiceRepository invoiceRepository;

        @Override
        public Invoice save(Invoice invoice){
            return invoiceRepository.save(invoice);
        }

         @Override
         public List<Invoice> findAll(){
            return (List<Invoice>) invoiceRepository.findAll();
         }

    @Override
    public Invoice findById(Long id) {

        return  invoiceRepository.findById(id).orElse(null);
    }
    @Override
    public Invoice update( Invoice invoice, Long id){
        Invoice invoiceCurrent =findById(id);

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
    public Optional<Invoice> findByDateBetween(LocalDate date1, LocalDate date2) {
        return invoiceRepository.findByDateBetween(date1,date2);
    }


}
