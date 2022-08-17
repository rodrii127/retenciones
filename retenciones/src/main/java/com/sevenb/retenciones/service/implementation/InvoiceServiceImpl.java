package com.sevenb.retenciones.service.implementation;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityExistsException;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.dto.BearerTokenPayloadDto;
import com.sevenb.retenciones.dto.InvoiceDto;
import com.sevenb.retenciones.dto.SearchInvoiceInputDto;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.repository.CompanyRepository;
import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.repository.ProviderRepository;
import com.sevenb.retenciones.repository.UserRepository;
import com.sevenb.retenciones.service.definition.InvoiceService;
import com.sevenb.retenciones.service.mapper.InvoiceMapper;
import com.sevenb.retenciones.utils.JWTExtractionUtil;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final JWTExtractionUtil jwtExtractionUtil;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CompanyRepository companyRepository,
                              ProviderRepository providerRepository, UserRepository userRepository,
                              JWTExtractionUtil jwtExtractionUtil) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
        this.jwtExtractionUtil = jwtExtractionUtil;
    }

    private final static InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);

    @Override
    public ResponseEntity<?> save(InvoiceDto invoiceDto, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        Invoice invoice = invoiceMapper.sourceToDestination(invoiceDto);
        invoice.setProvider(providerRepository.findById(invoiceDto.getProvider()).get());
        invoice.setCompany(bearerTokenPayloadDto.getCompany());

        List<Invoice> repeatedInvoices = invoiceRepository.findByPointSaleAndNumberAndProviderAndCompany(invoice.getPointSale(),
            invoice.getNumber(), invoice.getProvider(), invoice.getCompany());
        if (repeatedInvoices.isEmpty())
            return new ResponseEntity<>(invoiceRepository.save(invoice), HttpStatus.CREATED);
        throw new EntityExistsException("invoice-service.invoice.already-exist");
    }

    @Override
    public List<Invoice> findAll() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice update(InvoiceDto invoiceDto, Long id) {
        Invoice updateInvoice = findById(id);
        if (Objects.isNull(updateInvoice))
            throw new NotFoundException("invoice-service.invoice.not-found");
        updateInvoice.setNumber(invoiceDto.getNumber());
        updateInvoice.setPointSale(invoiceDto.getPointSale());
        updateInvoice.setProvider(providerRepository.findById(invoiceDto.getProvider()).get());
        updateInvoice.setEngraved(invoiceDto.getEngraved());
        updateInvoice.setExempt(invoiceDto.getExempt());
        updateInvoice.setIva105(invoiceDto.getIva105());
        updateInvoice.setIva21(invoiceDto.getIva21());
        updateInvoice.setIibb(invoiceDto.getIibb());
        updateInvoice.setTaxedOthers(invoiceDto.getTaxedOthers());
        updateInvoice.setMunicipality(invoiceDto.getMunicipality());
        updateInvoice.setImpacted(invoiceDto.getImpacted());
        return invoiceRepository.save(updateInvoice);
    }

    @Override
    public List<Invoice> findByFilters(SearchInvoiceInputDto invoiceDto, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        if (invoiceDto.getIdProvider() != null && invoiceDto.getImpacted() != null) {
            Provider provider = providerRepository.findById(invoiceDto.getIdProvider()).get();
            return invoiceRepository.findByDateBetweenAndProviderAndImpactedAndCompany(invoiceDto.getStarDate(),
                invoiceDto.getEndDate(), provider, invoiceDto.getImpacted(), bearerTokenPayloadDto.getCompany());
        }
        if (invoiceDto.getIdProvider() == null && invoiceDto.getImpacted() != null) {
            return invoiceRepository.findByDateBetweenAndImpactedAndCompany(invoiceDto.getStarDate(),
                invoiceDto.getEndDate(), invoiceDto.getImpacted(), bearerTokenPayloadDto.getCompany());
        }
        if (invoiceDto.getIdProvider() != null && invoiceDto.getImpacted() == null) {
            Provider provider = providerRepository.findById(invoiceDto.getIdProvider()).get();
            return invoiceRepository.findByDateBetweenAndProviderAndCompany(invoiceDto.getStarDate(),
                invoiceDto.getEndDate(), provider, bearerTokenPayloadDto.getCompany());
        }
        return invoiceRepository.findByDateBetweenAndCompany(invoiceDto.getStarDate(),
            invoiceDto.getEndDate(), bearerTokenPayloadDto.getCompany());
    }
}
