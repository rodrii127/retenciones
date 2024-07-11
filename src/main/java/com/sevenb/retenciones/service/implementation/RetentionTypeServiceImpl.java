package com.sevenb.retenciones.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.RetentionType;
import com.sevenb.retenciones.repository.RetentionTypeRepository;
import com.sevenb.retenciones.service.definition.RetentionTypeService;

@Service
public class RetentionTypeServiceImpl implements RetentionTypeService {

        private final RetentionTypeRepository retentionTypeRepository;

    public RetentionTypeServiceImpl(RetentionTypeRepository retentionTypeRepository) {
        this.retentionTypeRepository = retentionTypeRepository;
    }

    @Override
    public ResponseEntity<?> getRetentionType() {
        List<RetentionType> retentionTypesList = retentionTypeRepository.findAll();
        if (!retentionTypesList.isEmpty())
            return ResponseEntity.ok(retentionTypesList);
        throw new NotFoundException("retention-type-service.retention-type.not-found");
    }
}
