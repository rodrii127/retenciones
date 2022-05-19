package com.sevenb.retenciones.repository;

import com.sevenb.retenciones.entity.PayOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayOrderRepository extends JpaRepository<PayOrder, Long> {
}
