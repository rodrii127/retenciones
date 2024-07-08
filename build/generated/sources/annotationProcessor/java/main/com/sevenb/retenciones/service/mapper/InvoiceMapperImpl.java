package com.sevenb.retenciones.service.mapper;

import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.entity.Invoice;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-20T21:44:13-0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.jar, environment: Java 11.0.12.8 (Alibaba)"
)
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice sourceToDestination(InvoiceDto invoiceDto) {
        if ( invoiceDto == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( invoiceDto.getId() );
        invoice.setPointSale( invoiceDto.getPointSale() );
        invoice.setNumber( invoiceDto.getNumber() );
        invoice.setEngraved( invoiceDto.getEngraved() );
        invoice.setExempt( invoiceDto.getExempt() );
        invoice.setIva105( invoiceDto.getIva105() );
        invoice.setIva21( invoiceDto.getIva21() );
        invoice.setIibb( invoiceDto.getIibb() );
        invoice.setTaxedOthers( invoiceDto.getTaxedOthers() );
        invoice.setMunicipality( invoiceDto.getMunicipality() );
        invoice.setImpacted( invoiceDto.getImpacted() );
        invoice.setDate( invoiceDto.getDate() );

        return invoice;
    }
}
