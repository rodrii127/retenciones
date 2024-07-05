package com.sevenb.retenciones.service.implementation;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
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
import com.sevenb.retenciones.dto.PayOrderOutputDto;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.RetentionType;
import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.repository.PayOrderRepository;
import com.sevenb.retenciones.repository.RetentionRepositoy;
import com.sevenb.retenciones.repository.RetentionTypeRepository;
import com.sevenb.retenciones.service.definition.PayOrderService;
import com.sevenb.retenciones.utils.JWTExtractionUtil;
import com.sevenb.retenciones.utils.PayOrderPdf;

@Service
public class PayOrderServiceImpl implements PayOrderService {

    private final RetentionRepositoy retentionRepository;
    private final InvoiceRepository invoiceRepository;
    private final PayOrderRepository payOrderRepository;
    private final RetentionTypeRepository retentionTypeRepository;
    private final JWTExtractionUtil jwtExtractionUtil;

    public PayOrderServiceImpl(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository,
                               PayOrderRepository payOrderRepository, RetentionTypeRepository retentionTypeRepository,
                               JWTExtractionUtil jwtExtractionUtil) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.payOrderRepository = payOrderRepository;
        this.retentionTypeRepository = retentionTypeRepository;
        this.jwtExtractionUtil = jwtExtractionUtil;
    }

    @Override
    public ResponseEntity<?> findAll(LocalDate startDate, LocalDate endDate, Long providerId, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);

        List<PayOrder> payOrderList = payOrderRepository.findAllWithFilters(startDate, endDate, providerId, bearerTokenPayloadDto.getCompany().getId());
        payOrderList = payOrderList.stream().filter(f-> !Boolean.FALSE.equals(f.getActive())).collect(Collectors.toList());
        List<PayOrderOutputDto> payOrderOutputDtos = new ArrayList<>();
        for (PayOrder p : payOrderList) {
            PayOrderOutputDto payOrderOutputDto = new PayOrderOutputDto();
            payOrderOutputDto.setDate(p.getDate());
            payOrderOutputDto.setProvider(p.getProvider().getCompanyName());
            payOrderOutputDto.setCuitProvider(p.getProvider().getCuit());
            payOrderOutputDto.setNumber(p.getPayOrderNumber());
            payOrderOutputDto.setBase(p.calculateBase().toString());
            payOrderOutputDto.setRetention(String.valueOf(p.calculateTotal() - p.calculateTotalWithRetentions()));
            payOrderOutputDto.setAmountPaid(p.calculateTotalWithRetentions().toString());
            payOrderOutputDtos.add(payOrderOutputDto);
        }
        if (!payOrderList.isEmpty())
            return new ResponseEntity<>(payOrderOutputDtos, HttpStatus.CREATED);
        throw new NotFoundException("PayOrder-service.retention.not-found");
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

    /*TODO desde mi punto de vista esto debe borrarse*/
    @Override
    public ResponseEntity<?> findByDateBetween(LocalDate startDate, LocalDate endDate, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        List<PayOrder> payOrderList = payOrderRepository.findByDateBetweenAndCompany(startDate, endDate, bearerTokenPayloadDto.getCompany());
        payOrderList = payOrderList.stream().filter(f-> !Boolean.FALSE.equals(f.getActive())).collect(Collectors.toList());
        List<PayOrderOutputDto> payOrderOutputDtos = new ArrayList<>();
        for (PayOrder p : payOrderList) {
            PayOrderOutputDto payOrderOutputDto = new PayOrderOutputDto();
            payOrderOutputDto.setId(p.getId());
            payOrderOutputDto.setDate(p.getDate());
            payOrderOutputDto.setProvider(p.getProvider().getCompanyName());
            payOrderOutputDto.setCuitProvider(p.getProvider().getCuit());
            payOrderOutputDto.setNumber(p.getPayOrderNumber());
            payOrderOutputDto.setBase(String.format("%.2f",p.calculateBase()));
            payOrderOutputDto.setRetention(String.format("%.2f",(p.calculateTotal() - p.calculateTotalWithRetentions())));
            payOrderOutputDto.setAmountPaid(String.format("%.2f",p.calculateTotalWithRetentions()));
            payOrderOutputDtos.add(payOrderOutputDto);
        }
        if (!payOrderList.isEmpty())
            return new ResponseEntity<>(payOrderOutputDtos, HttpStatus.OK);
        throw new NotFoundException("PayOrder-service.retention.not-found");
    }

    @Override
    public FileOutputStream createInfoByDateXls(LocalDate startDate, LocalDate endDate, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        List<PayOrder> payOrderList = payOrderRepository.findByDateBetweenAndCompany(startDate, endDate, bearerTokenPayloadDto.getCompany());

        return null;
    }

    @Override
    public void deleteById(Long id, boolean logicalDelete, String bearerToken) {
        Optional<PayOrder> optionalPayOrder = payOrderRepository.findById(id);
        if (optionalPayOrder.isEmpty())
            throw new NotFoundException("payOrder-service.pay-order.not-found");
        PayOrder payOrder = optionalPayOrder.get();
        if (!isSameCompany(payOrder, bearerToken))
            throw new NotFoundException("payOrder-service.pay-order.not-found");

        if (logicalDelete) {
            payOrder.setActive(false);
            for (Invoice invoice:payOrder.getInvoice()){
                invoice.setImpacted(false);
            }
            payOrderRepository.save(payOrder);
            payOrder.setInvoice(null);
            for (Retention retention:payOrder.getRetentionList()){
                retention.setLogicalDelete(true);
            }
            payOrderRepository.save(payOrder);
        } else {
            payOrderRepository.deleteById(id);
        }
    }

    private boolean isSameCompany(PayOrder payOrder, String bearerToken) {
        BearerTokenPayloadDto bearerTokenPayloadDto = jwtExtractionUtil.getPayloadFromToken(bearerToken);
        return Optional.ofNullable(payOrder.getCompany())
            .map(Company::getId)
            .map(id -> id.equals(Optional.ofNullable(bearerTokenPayloadDto.getCompany())
                .map(Company::getId)
                .orElse(null)))
            .orElse(false);
    }
}
