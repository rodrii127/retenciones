package com.sevenb.retenciones.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sevenb.retenciones.entity.Invoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByDateBetweenAndProviderAndImpactedAndCompany(LocalDate starDate, LocalDate endDate, Provider provider, Boolean impacted, Company company);
    List<Invoice> findByDateBetweenAndImpactedAndCompany(LocalDate starDate, LocalDate endDate,Boolean impacted, Company company);
    List<Invoice> findByDateBetweenAndProviderAndCompany(LocalDate starDate, LocalDate endDate, Provider provider,Company company);
    List<Invoice> findByDateBetweenAndCompany(LocalDate starDate, LocalDate endDate,Company company);
    List<Invoice> findByPointSaleAndNumberAndProviderAndCompany(Integer pointSale, Long number, Provider provider, Company company);

    @Modifying
    @Query("update Invoice i set i.impacted = true where i.id = :id")
    void updateImpactedTrue(@Param("id") Long id);
}
