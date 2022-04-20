package com.sevenb.retenciones.repository;

import com.sevenb.retenciones.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface InvoiceRepository extends JpaRepository<Invoice,Long> {


 Optional<Invoice> findByDateBetween(LocalDate date1 , LocalDate date2);



}
