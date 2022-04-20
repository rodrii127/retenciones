package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.repository.CompanyRepository;
import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.repository.RetentionRepositoy;
import com.sevenb.retenciones.repository.RetentionTypeRepository;
import com.sevenb.retenciones.service.definition.RetentionService;

import com.sevenb.retenciones.utils.RetentionPdf;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RetentionServiceImp implements RetentionService {

    private static final Logger LOG = LoggerFactory.getLogger(RetentionServiceImp.class);

    private final RetentionRepositoy retentionRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final RetentionTypeRepository retentionTypeRepository;


    @Autowired
    public RetentionServiceImp(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository, CompanyRepository companyRepository, RetentionTypeRepository retentionTypeRepository) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.retentionTypeRepository = retentionTypeRepository;
    }


    @Override
    public ResponseEntity<?> saveRetention(List<Long> invoices) {
        Retention retention = new Retention();
        List<Invoice> listInvoice = new ArrayList<>();
        invoices.forEach(i->listInvoice.add(invoiceRepository.findById(i).get()));
        retention.setInvoice(listInvoice);
        retention.setCompany(companyRepository.findById(1L).get());
        retention.setDate(LocalDate.now());
        retention.setRetentionType(retentionTypeRepository.findById(1L).get());
        retention.setRetentionAmount(retention.calculateRet());
        return new ResponseEntity<>(retentionRepository.save(retention), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<Retention> retentions = retentionRepository.findAll();
        if (CollectionUtils.isNotEmpty(retentions)) {
            return new ResponseEntity<>(retentions, HttpStatus.OK);
        }
        throw new NotFoundException("retention-service.retention.not-found");
    }

    @Override
    public ResponseEntity<?> findOneRetention(Long id) {
        Optional<Retention> retention = retentionRepository.findById(id);
        if (retention.isPresent()) {
            return new ResponseEntity<>(retention, HttpStatus.OK);
        }
        throw new NotFoundException("retention-service.retention.not-found");
    }

    @Override
    public ByteArrayInputStream retentionPdf(Long id){
        Optional<Retention> retention = retentionRepository.findById(id);
        RetentionPdf retentionPdf = new RetentionPdf();
        if(retention.isPresent())
          return retentionPdf.generatePdf(retention.get());
        throw new NotFoundException("retentionPdf-service.retention.not-found");
    }

}
