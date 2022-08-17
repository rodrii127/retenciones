package com.sevenb.retenciones.service.implementation;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.dto.BearerTokenPayloadDto;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.RetentionType;
import com.sevenb.retenciones.repository.CompanyRepository;
import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.repository.PayOrderRepository;
import com.sevenb.retenciones.repository.ProviderRepository;
import com.sevenb.retenciones.repository.RetentionRepositoy;
import com.sevenb.retenciones.repository.RetentionTypeRepository;
import com.sevenb.retenciones.repository.UserRepository;
import com.sevenb.retenciones.service.definition.PayOrderService;
import com.sevenb.retenciones.utils.JWTExtractionUtil;
import com.sevenb.retenciones.utils.PayOrderPdf;

@Service
public class PayOrderServiceImpl implements PayOrderService {

    private final RetentionRepositoy retentionRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final PayOrderRepository payOrderRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
    private final RetentionTypeRepository retentionTypeRepository;
    private final JWTExtractionUtil jwtExtractionUtil;

    public PayOrderServiceImpl(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository,
                               CompanyRepository companyRepository, PayOrderRepository payOrderRepository,
                               UserRepository userRepository, ProviderRepository providerRepository,
                               RetentionTypeRepository retentionTypeRepository, JWTExtractionUtil jwtExtractionUtil) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.payOrderRepository = payOrderRepository;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.retentionTypeRepository = retentionTypeRepository;
        this.jwtExtractionUtil = jwtExtractionUtil;
    }

    @Override
    @Transactional
    public ResponseEntity<?> savePayOrder(List<Long> idInvoices, LocalDate startDate, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        PayOrder payOrder = new PayOrder();
        List<Retention> retentionList = new ArrayList<>();
        List<Invoice> invoiceList = new ArrayList<>();
        idInvoices.forEach(i -> {
            invoiceList.add(invoiceRepository.findById(i).get());
            invoiceRepository.updateImpactedTrue(i);
        });
        payOrder.setInvoice(invoiceList);
        Provider provider = invoiceList.get(NumberUtils.INTEGER_ZERO).getProvider();
        if (bearerTokenPayloadDto.getCompany().getIibb() && BooleanUtils.isFalse(provider.getIibbExcept())) {
            retentionList.add(saveRetention(payOrder.calculateBase(), startDate, 2L, bearerTokenPayloadDto.getCompany(), provider));
        }
        if (bearerTokenPayloadDto.getCompany().getMunicipalityRet() && BooleanUtils.isFalse(provider.getMunicipalityExcept())) {
            retentionList.add(saveRetention(payOrder.calculateBase(), startDate, 1L, bearerTokenPayloadDto.getCompany(), provider));
        }
        payOrder.setRetentionList(retentionList.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        payOrder.setCompany(bearerTokenPayloadDto.getCompany());
        payOrder.setPayMode("EFECTIVO");
        payOrder.setDate(startDate);
        payOrder.setPayModeNumber("");
        payOrder.setProvider(invoiceList.get(NumberUtils.INTEGER_ZERO).getProvider());
        Long number = payOrderRepository.findMaxPayOrder(bearerTokenPayloadDto.getCompany());
        if (number != null) {
            payOrder.setPayOrderNumber(String.valueOf(number + 1));
        } else {
            payOrder.setPayOrderNumber(String.valueOf(1));
        }
        return new ResponseEntity<>(payOrderRepository.save(payOrder), HttpStatus.CREATED);
    }

    public Retention saveRetention(Double base, LocalDate startDate, Long idRetention, Company company, Provider provider) {
        RetentionType retentionType = retentionTypeRepository.findById(idRetention).orElse(null);
        Optional<Double> amount = Optional.ofNullable(retentionType)
            .map(retentionTypeLambda -> provider.getAgreement() ? retentionTypeLambda.getReducedAliquot() : retentionTypeLambda.getAliquot())
            .map(aliquot -> aliquot * base);
        if (amount.isPresent() && (amount.get() >= retentionType.getMinimumAmount())) {
            Retention retention = new Retention();
            retention.setRetentionAmount(amount.get());
            retention.setDate(startDate);
            retention.setRetentionType(retentionType);
            retention.setProvider(provider);
            Long number = retentionRepository.findMaxRetention(company, retention.getRetentionType());
            retention.setNumber(Objects.nonNull(number) ? (number + 1) : 1L);
            retention.setCompany(company);
            retentionRepository.save(retention);
            return retention;
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(payOrderRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findOnePayOrder(Long id) {
        Optional<PayOrder> payOrder = payOrderRepository.findById(id);
        return payOrder.isPresent()
            ? new ResponseEntity<>(payOrder, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ByteArrayInputStream payOderPdf(Long id) {
        Optional<PayOrder> payOrder = payOrderRepository.findById(id);
        PayOrderPdf payOrderPdf = new PayOrderPdf();
        if (payOrder.isPresent())
            return payOrderPdf.generatePdfPayOrder(payOrder.get());
        throw new NotFoundException("payOrder-service.retention.not-found");
    }

    @Override
    public ResponseEntity<?> findByDateBetween(LocalDate startDate, LocalDate endDate, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        List<PayOrder> payOrderList = payOrderRepository.findByDateBetweenAndCompany(startDate, endDate, bearerTokenPayloadDto.getCompany());
        if (payOrderList.isEmpty())
            return new ResponseEntity<>(payOrderList, HttpStatus.CREATED);
        throw new NotFoundException("PayOrder-service.retention.not-found");
    }
}
