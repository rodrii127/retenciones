package com.sevenb.retenciones.service.definition;

import com.sevenb.retenciones.dto.RetentionInputDto;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.RetentionType;
import com.sevenb.retenciones.entity.SearchDate;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public interface RetentionService {

   //public ResponseEntity<?> saveRetention(RetentionInputDto inputDto);

    public ResponseEntity<?> findAll(LocalDate startDate , LocalDate endDate,Long id, String bearerToken);
    public ResponseEntity<?> findOneRetention(Long id);
    //public ByteArrayInputStream retentionPdf(Long id);
    public File generaFileMunicipality(LocalDate startDate, LocalDate endDate, Long idRetentionType, String bearerToken) throws Exception;

}
