package com.sevenb.retenciones.service.implementation;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.collections4.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.dto.BearerTokenPayloadDto;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.repository.CompanyRepository;
import com.sevenb.retenciones.repository.ProviderRepository;
import com.sevenb.retenciones.repository.UserRepository;
import com.sevenb.retenciones.service.definition.ProviderService;
import com.sevenb.retenciones.utils.JWTExtractionUtil;

/**
 * ProviderService implementation. Defines how methods will access Providers database.
 */
@Service
public class ProviderServiceImp implements ProviderService {

    private static final Logger LOG = LoggerFactory.getLogger(ProviderServiceImp.class);

    private final ProviderRepository providerRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final JWTExtractionUtil jwtExtractionUtil;

    @Autowired
    public ProviderServiceImp(ProviderRepository providerRepository, CompanyRepository companyRepository,
                              UserRepository userRepository, JWTExtractionUtil jwtExtractionUtil) {
        this.providerRepository = providerRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.jwtExtractionUtil = jwtExtractionUtil;
    }

    @Override
    public ResponseEntity<?> saveProvider(Provider provider, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        Optional<Provider> duplicatedCompany = providerRepository.findByCompanyNameAndCuitAndCompany(provider.getCompanyName(),
            provider.getCuit(), bearerTokenPayloadDto.getCompany());
        provider.setCompany(bearerTokenPayloadDto.getCompany());
        if (duplicatedCompany.isEmpty()) {
            return new ResponseEntity<>(providerRepository.save(provider), HttpStatus.CREATED);
        }
        LOG.error("Provider already exist by companyName or cuit.");
        throw new EntityExistsException("provider-service.provider.entity-already-exists");
    }

    @Override
    public ResponseEntity<?> findAll(String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        List<Provider> providers = providerRepository.findByCompany(bearerTokenPayloadDto.getCompany()).orElse(null);
        if (CollectionUtils.isNotEmpty(providers)) {
            return new ResponseEntity<>(providers, HttpStatus.OK);
        }
        throw new NotFoundException("provider-service.provider.not-found");
    }

    @Override
    public ResponseEntity<?> findOneProvider(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            return new ResponseEntity<>(provider, HttpStatus.OK);
        }
        throw new NotFoundException("provider-service.provider.not-found");
    }

    @Override
    public ResponseEntity<?> update(Long id, Provider providerNew) {
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            provider.setAddress(providerNew.getAddress());
            provider.setCompanyName(providerNew.getCompanyName());
            provider.setCuit(providerNew.getCuit());
            provider.setPhone(providerNew.getPhone());
            provider.setFiscalCondition(providerNew.getFiscalCondition());
            provider.setAgreement(providerNew.getAgreement());
            provider.setIibbExcept(providerNew.getIibbExcept());
            provider.setMunicipalityExcept(providerNew.getMunicipalityExcept());
            return new ResponseEntity<>(providerRepository.save(provider), HttpStatus.OK);
        }
        throw new NotFoundException("provider-service.provider.not-found");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            providerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new NotFoundException("provider-service.provider.not-found");
        }
    }
}
