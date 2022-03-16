package com.sevenb.retenciones.dao;

import com.sevenb.retenciones.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceDao extends JpaRepository<Invoice,Long> {

 }
