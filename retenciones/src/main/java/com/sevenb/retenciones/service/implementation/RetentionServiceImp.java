package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.dto.RetentionInputDto;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.repository.*;
import com.sevenb.retenciones.security.JWTValidator;
import com.sevenb.retenciones.security.entity.JWTUser;
import com.sevenb.retenciones.service.definition.RetentionService;

import com.sevenb.retenciones.utils.MunicipalityCsvUtil;
import com.sevenb.retenciones.utils.RetentionPdf;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
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
    private final UserRepository userRepository;

    private final JWTValidator jwtValidator;
    private  String username;


    @Autowired
    public RetentionServiceImp(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository, CompanyRepository companyRepository, RetentionTypeRepository retentionTypeRepository, UserRepository userRepository, JWTValidator jwtValidator) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.retentionTypeRepository = retentionTypeRepository;
        this.userRepository = userRepository;

        this.jwtValidator = jwtValidator;
    }


    @Override
    public ResponseEntity<?> saveRetention(RetentionInputDto inputDto) {
          username = SecurityContextHolder.getContext().getAuthentication().getName();
        Retention retention = new Retention();
        List<Invoice> listInvoice = new ArrayList<>();
        inputDto.getIdIvoices().forEach(i->listInvoice.add(invoiceRepository.findById(i).get()));
        retention.setInvoice(listInvoice);
        Company company = companyRepository.findById(userRepository.findByUsername(username).getIdUser()).get();
        retention.setCompany(company);
        retention.setDate(inputDto.getStartDate());
        retention.setRetentionType(retentionTypeRepository.findById(inputDto.getIdRetentionType()).get());
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

    @Override
    public File generaFileMunicipality(List<Long> retentionsId) {
        List<Retention> retentionList = new ArrayList<>();
        retentionsId.forEach(r-> retentionList.add(retentionRepository.findById(r).get()));
        MunicipalityCsvUtil municipalityCsvUtil  = new MunicipalityCsvUtil();
        Company company = companyRepository.findById(2L).get();

        return municipalityCsvUtil.generaFileMunicipality(retentionList, company);
    }
}


