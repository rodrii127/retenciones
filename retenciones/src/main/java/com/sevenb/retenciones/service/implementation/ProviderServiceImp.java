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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.repository.CompanyRepository;
import com.sevenb.retenciones.repository.ProviderRepository;
import com.sevenb.retenciones.repository.UserRepository;
import com.sevenb.retenciones.service.definition.ProviderService;

/**
 * ProviderService implementation. Defines how methods will access Providers database.
 */
@Service
public class ProviderServiceImp implements ProviderService {

    private static final Logger LOG = LoggerFactory.getLogger(ProviderServiceImp.class);

    private final ProviderRepository providerRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProviderServiceImp(ProviderRepository providerRepository, CompanyRepository companyRepository,
                              UserRepository userRepository) {
        this.providerRepository = providerRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> saveProvider(Provider provider) {
        Optional<Provider> providerByCompany = providerRepository.findByCompanyName(provider.getCompanyName());
        Optional<Provider> providerByCuit = providerRepository.findByCuit(provider.getCuit());
        provider.setCompany(getCompanyByToken());
        if (providerByCompany.isEmpty() && providerByCuit.isEmpty()) {
            return new ResponseEntity<>(providerRepository.save(provider), HttpStatus.CREATED);
        }
        LOG.error("Provider already exist by companyName or cuit.");
        throw new EntityExistsException("provider-service.provider.entity-already-exists");
    }

    @Override
    public ResponseEntity<?> findAll() {
        Company company = getCompanyByToken();
        List<Provider> providers = providerRepository.findByCompany(company).orElse(null);

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
            return new ResponseEntity<>(provider, HttpStatus.OK);
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

    private Company getCompanyByToken() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyRepository.findById(userRepository.findByUsername(username).getIdUser()).orElseGet(Company::new);
    }
}
