package com.sevenb.retenciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sevenb.retenciones.entity.Provider;

/**
 * Provider repository.
 */
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Optional<Provider> findByCompanyName(String companyName);

    Optional<Provider> findByCuit(String cuit);
}
