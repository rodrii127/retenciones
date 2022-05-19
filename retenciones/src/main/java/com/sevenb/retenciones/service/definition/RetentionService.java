package com.sevenb.retenciones.service.definition;

import com.sevenb.retenciones.dto.RetentionInputDto;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Retention;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

public interface RetentionService {

   public ResponseEntity<?> saveRetention(RetentionInputDto inputDto);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findOneRetention(Long id);

    public ByteArrayInputStream retentionPdf(Long id);

    public File generaFileMunicipality(List<Long> retentionsId);

}
