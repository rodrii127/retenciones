package com.sevenb.retenciones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Provider;

/**
 * Provider repository.
 */
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Optional<List<Provider>> findByCompany(Company company);

    Optional<Provider> findByCompanyNameAndCuitAndCompany(String companyName, String cuit, Company company);
}
