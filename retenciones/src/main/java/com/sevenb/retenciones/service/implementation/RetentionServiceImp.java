package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.dto.RetentionInputDto;
import com.sevenb.retenciones.entity.*;
import com.sevenb.retenciones.repository.*;
import com.sevenb.retenciones.security.JWTValidator;
import com.sevenb.retenciones.security.entity.JWTUser;
import com.sevenb.retenciones.security.entity.JWTUserDetails;
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
    private final UserRepository userRepository;

    private final JWTValidator jwtValidator;
    private String username;


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
    public ResponseEntity<?> findAll(LocalDate startDate , LocalDate endDate, Long idRetentiontype) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Company company = companyRepository.findById(userRepository.findByUsername(username).getIdUser()).get();
        RetentionType retentionType = retentionTypeRepository.findById(idRetentiontype).get();
        List<Retention> retentions = retentionRepository.findByDateBetweenAndCompanyAndRetentionType(startDate,endDate,company,retentionType);
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
    public File generaFileMunicipality(LocalDate startDate, LocalDate endDate, Long idRetentionType) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Company company = companyRepository.findById(userRepository.findByUsername(username).getIdUser()).get();
        RetentionType retentionType = retentionTypeRepository.findById(idRetentionType).get();
        List<Retention> retentionList = retentionRepository.findByDateBetweenAndCompanyAndRetentionType(startDate,endDate ,company, retentionType);
        MunicipalityCsvUtil municipalityCsvUtil = new MunicipalityCsvUtil();
        return municipalityCsvUtil.generaFileMunicipality(retentionList, company);
    }
    }


