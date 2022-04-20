package com.sevenb.retenciones.repository;

import com.sevenb.retenciones.entity.RetentionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetentionTypeRepository extends JpaRepository<RetentionType,Long> {
}
