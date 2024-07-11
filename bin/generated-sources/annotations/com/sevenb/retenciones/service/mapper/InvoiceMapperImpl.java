package com.sevenb.retenciones.service.mapper;

import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.entity.Invoice;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-11T12:31:47-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
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
