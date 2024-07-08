package com.sevenb.retenciones.repository;

import com.sevenb.retenciones.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
