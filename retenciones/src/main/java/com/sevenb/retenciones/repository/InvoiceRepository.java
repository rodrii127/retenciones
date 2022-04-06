package com.sevenb.retenciones.repository;

import com.sevenb.retenciones.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

 }
