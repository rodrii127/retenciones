package com.sevenb.retenciones.service.implementation;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sevenb.retenciones.dto.ConvertInputDto;
import com.sevenb.retenciones.repository.*;
import com.sevenb.retenciones.repository.enums.RetentionTypeEnum;
import com.sevenb.retenciones.utils.RetentionATMCsvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.collections4.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.dto.BearerTokenPayloadDto;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.RetentionType;
import com.sevenb.retenciones.security.JWTValidator;
import com.sevenb.retenciones.service.definition.RetentionService;
import com.sevenb.retenciones.utils.JWTExtractionUtil;
import com.sevenb.retenciones.utils.MunicipalityCsvUtil;

@Service
public class RetentionServiceImp implements RetentionService {

    private static final Logger LOG = LoggerFactory.getLogger(RetentionServiceImp.class);

    private final RetentionRepositoy retentionRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final RetentionTypeRepository retentionTypeRepository;
    private final UserRepository userRepository;
    private final JWTValidator jwtValidator;
    private final JWTExtractionUtil jwtExtractionUtil;
    MunicipalityCsvUtil municipalityCsvUtil ;
    RetentionATMCsvUtil retentionATMCsvUtil;


    @Autowired
    public RetentionServiceImp(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository,
                               CompanyRepository companyRepository, RetentionTypeRepository retentionTypeRepository,
                               UserRepository userRepository, JWTValidator jwtValidator,
                               JWTExtractionUtil jwtExtractionUtil) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.retentionTypeRepository = retentionTypeRepository;
        this.userRepository = userRepository;
        this.jwtValidator = jwtValidator;
        this.jwtExtractionUtil = jwtExtractionUtil;
        this.municipalityCsvUtil = new MunicipalityCsvUtil();
        this.retentionATMCsvUtil = new RetentionATMCsvUtil();
    }

    @Override
    public ResponseEntity<?> findAll(LocalDate startDate, LocalDate endDate, Long idRetentiontype, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        RetentionType retentionType = retentionTypeRepository.findById(idRetentiontype).get();
        List<Retention> retentions = retentionRepository.findByDateBetweenAndCompanyAndRetentionType(startDate, endDate,
            bearerTokenPayloadDto.getCompany(), retentionType);
        if (CollectionUtils.isNotEmpty(retentions)) {
            return new ResponseEntity<>(retentions, HttpStatus.OK);
        }
        throw new NotFoundException("retention-service.retention.not-found");
    }

    @Override
    public ResponseEntity<?> findOneRetention(Long id) {
        Optional<Retention> retention = retentionRepository.findById(id);
        if (retention.isPresent())
            return new ResponseEntity<>(retention, HttpStatus.OK);

        throw new NotFoundException("retention-service.retention.not-found");
    }

    @Override
    public File retentionCsv(LocalDate startDate, LocalDate endDate, Long idRetentionType, String bearerToken) throws Exception {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        RetentionType retentionType = retentionTypeRepository.findById(idRetentionType).get();
        List<Retention> retentionList = retentionRepository.findByDateBetweenAndCompanyAndRetentionType(startDate, endDate,
                bearerTokenPayloadDto.getCompany(), retentionType);
        retentionList = retentionList.stream()
                .filter(r -> !Boolean.TRUE.equals(r.getLogicalDelete())).collect(Collectors.toList());
        System.out.println(RetentionTypeEnum.MUNICIPALITY.getValue());
        if(retentionType.getId().equals(RetentionTypeEnum.MUNICIPALITY.getValue()))
            return municipalityCsvUtil.generaFileMunicipality(retentionList, bearerTokenPayloadDto.getCompany());
        if(retentionType.getId().equals(RetentionTypeEnum.ATM.getValue()))
            return retentionATMCsvUtil.fileAtmCsv(retentionList, bearerTokenPayloadDto.getCompany());

        throw new NotFoundException("retention-service.retention.not-found");
    }

    public File convertRetentionAtmCsv(List<ConvertInputDto> listRetention) throws Exception {
              return retentionATMCsvUtil.convertFileAtmCsv(listRetention);
    }
}


