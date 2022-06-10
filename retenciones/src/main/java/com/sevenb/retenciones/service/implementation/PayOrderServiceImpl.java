package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.*;
import com.sevenb.retenciones.repository.*;
import com.sevenb.retenciones.service.definition.PayOrderService;
import com.sevenb.retenciones.utils.PayOrderPdf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PayOrderServiceImpl implements PayOrderService {

    private final RetentionRepositoy retentionRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final PayOrderRepository payOrderRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;

    private final RetentionTypeRepository retentionTypeRepository;
    public PayOrderServiceImpl(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository, CompanyRepository companyRepository, PayOrderRepository payOrderRepository, UserRepository userRepository, ProviderRepository providerRepository, RetentionTypeRepository retentionTypeRepository) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.payOrderRepository = payOrderRepository;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.retentionTypeRepository = retentionTypeRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> savePayOrder(List<Long> idInvoices, LocalDate startDate) {
        PayOrder payOrder = new PayOrder();
        List<Retention> retentionList = new ArrayList<>();
        List<Invoice> invoiceList = new ArrayList<>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Company company = companyRepository.findById(userRepository.findByUsername(username).getIdUser()).get();
        idInvoices.forEach(i->{
            invoiceList.add(invoiceRepository.findById(i).get());
            invoiceRepository.updateImpactedTrue(i);
        });
        payOrder.setInvoice(invoiceList);
        if (company.getIibb() == true){
           retentionList.add(saveRetention(payOrder.calculateIibb(),startDate,2l,company,invoiceList.get(0).getProvider()));
       }
        if (company.getMunicipalityRet() == true){
            Double p = payOrder.calculateMunicipality();
            retentionList.add(saveRetention(p,startDate,1L, company,invoiceList.get(0).getProvider()));
        }
        payOrder.setRetentionList(retentionList);
        payOrder.setCompany(company);
        payOrder.setPayMode("EFECTIVO");
        payOrder.setDate(startDate);
        payOrder.setPayModeNumber("");
        payOrder.setProvider(invoiceList.get(0).getProvider());
        Long number =payOrderRepository.findMaxPayOrder(company);
        if(number != null){
            payOrder.setPayOrderNumber(String.valueOf(number + 1));
        }else {
            payOrder.setPayOrderNumber(String.valueOf(1));
        }
        return new ResponseEntity<>(payOrderRepository.save(payOrder), HttpStatus.CREATED);
    }

    public Retention saveRetention(Double amount, LocalDate startDate, Long idRetention, Company company, Provider provider){
        Retention retention = new Retention();
        retention.setRetentionAmount(amount);
        retention.setDate(startDate);
        retention.setRetentionType(retentionTypeRepository.findById(idRetention).get());
        retention.setProvider(provider);
        Long number =retentionRepository.findMaxRetention(company,retention.getRetentionType());
        if(number != null){
            retention.setNumber(number +1);
        }else {
            retention.setNumber(1L);
        }
        retention.setCompany(company);
        retentionRepository.save(retention);
        return retention;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> findOnePayOrder(Long id) {
        return null;
    }

    @Override
    public ByteArrayInputStream payOderPdf(Long id) {
        Optional<PayOrder> payOrder = payOrderRepository.findById(id);
        PayOrderPdf payOrderPdf = new PayOrderPdf();
        if(payOrder.isPresent())
            return payOrderPdf.generatePdfPayOrder(payOrder.get());
        throw new NotFoundException("payOrder-service.retention.not-found");
    }

    @Override
    public ResponseEntity<?> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Company company = companyRepository.findById(userRepository.findByUsername(username).getIdUser()).get();
        List<PayOrder> payOrderList = payOrderRepository.findByDateBetweenAndCompany(startDate,endDate,company);
        if(payOrderList.isEmpty())
            return new ResponseEntity<>(payOrderList, HttpStatus.CREATED);
        throw new NotFoundException("PayOrder-service.retention.not-found");
    }

}
