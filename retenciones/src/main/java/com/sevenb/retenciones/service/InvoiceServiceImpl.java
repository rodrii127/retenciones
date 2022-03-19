package com.sevenb.retenciones.service;

import com.sevenb.retenciones.dao.InvoiceDao;
import com.sevenb.retenciones.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;


@Service
public class InvoiceServiceImpl implements InvoiceService {

        @Autowired
        private InvoiceDao invoiceDao;

        @Override
        public Invoice save(Invoice invoice){
            return invoiceDao.save(invoice);
        }

         @Override
         public List<Invoice> findAll(){
            return (List<Invoice>) invoiceDao.findAll();

         }

    @Override
    public Invoice findById(Long id) {

        return  invoiceDao.findById(id).orElse(null);
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

        return invoiceDao.save(invoiceCurrent);

    }


}
