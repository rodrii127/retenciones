package com.sevenb.retenciones.service.mapper;

import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
public interface InvoiceMapper {
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "provider", ignore = true)
    Invoice sourceToDestination(InvoiceDto invoiceDto);

}
