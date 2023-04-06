package com.sevenb.retenciones.controller.definition;

import com.sevenb.retenciones.dto.ConvertInputDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConvertController {
    ResponseEntity<?> convertInputAtm(List<ConvertInputDto> convertInputList) throws Exception;

}
