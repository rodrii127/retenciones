package com.sevenb.retenciones.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.PayOrder;

@Repository
public interface PayOrderRepository extends JpaRepository<PayOrder, Long> {

    @Query("SELECT p FROM PayOrder p WHERE (:providerId IS NULL OR p.provider.id = :providerId) " +
            "AND (CAST(:startDate AS date) IS NULL OR p.date >= :startDate)" +
            "AND (CAST(:endDate AS date) IS NULL OR p.date <= :endDate)" +
            "AND (:companyId IS NULL OR p.company.id = :companyId) ")/*TODO esto puede fallar para las fechas cuando una es null y la otra no*/
    List<PayOrder> findAllWithFilters(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                      @Param("providerId") Long providerId, @Param("companyId") Long companyId);

    @Query("select MAX(CAST(r.payOrderNumber as integer)) from PayOrder r where r.company = :company")
    Long findMaxPayOrder(@Param("company") Company company);

    List<PayOrder> findByDateBetweenAndCompany(LocalDate startDate, LocalDate endDate, Company company);


}
