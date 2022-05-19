package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.repository.CompanyRepository;
import com.sevenb.retenciones.repository.InvoiceRepository;
import com.sevenb.retenciones.repository.PayOrderRepository;
import com.sevenb.retenciones.repository.RetentionRepositoy;
import com.sevenb.retenciones.service.definition.PayOrderService;
import com.sevenb.retenciones.utils.PayOrderPdf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public PayOrderServiceImpl(RetentionRepositoy retentionRepository, InvoiceRepository invoiceRepository, CompanyRepository companyRepository, PayOrderRepository payOrderRepository) {
        this.retentionRepository = retentionRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.payOrderRepository = payOrderRepository;
    }

    @Override
    public ResponseEntity<?> savePayOrder(List<Long> idRetentions, LocalDate startDate) {
        PayOrder payOrder = new PayOrder();
        List<Retention> retentionList = new ArrayList<>();
        idRetentions.forEach(r-> {
            retentionList.add(retentionRepository.findById(r).get());
        });
        payOrder.setRetentionList(retentionList);
        payOrder.setCompany(retentionList.get(0).getCompany());
        payOrder.setPayMode("EFECTIVO");
        payOrder.setDate(startDate);
        payOrder.setPayModeNumber("");
        payOrder.setProvider(retentionList.get(0).getInvoice().get(0).getProvider());
        payOrder.setPayOrderNumber("1");
        return new ResponseEntity<>(payOrderRepository.save(payOrder), HttpStatus.CREATED);
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
        throw new NotFoundException("retentionPdf-service.retention.not-found");
    }

}
