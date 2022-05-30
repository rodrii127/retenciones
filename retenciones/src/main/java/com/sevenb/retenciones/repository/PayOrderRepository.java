package com.sevenb.retenciones.repository;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.RetentionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayOrderRepository extends JpaRepository<PayOrder, Long> {

    @Query("select MAX(r.payOrderNumber) from PayOrder r where r.company = :company")
    Long findMaxPayOrder(@Param("company") Company company);

}
