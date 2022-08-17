package com.sevenb.retenciones.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.RetentionType;

@Repository
public interface RetentionRepositoy extends JpaRepository<Retention, Long> {

    @Query("select MAX(r.number) from Retention r where r.company = :companyId and r.retentionType = :idRetentionType")
    Long findMaxRetention(@Param("companyId") Company companyId, @Param("idRetentionType") RetentionType idRetentionType);

    List<Retention> findByDateBetweenAndCompanyAndRetentionType(LocalDate starDate, LocalDate endDate, Company company, RetentionType retention);

}
