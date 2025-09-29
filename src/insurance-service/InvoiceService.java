package com.healthcare.insurance;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice generateInvoice(UUID patientId, BigDecimal amount, List<String> items) {
        Invoice invoice = new Invoice();
        invoice.setPatientId(patientId);
        invoice.setAmount(amount);
        invoice.setStatus(Invoice.PaymentStatus.PENDING);
        invoice.setDate(new Date());
        invoice.setItems(items);
        return invoiceRepository.save(invoice);
    }

    public Invoice updatePaymentStatus(UUID id, Invoice.PaymentStatus status) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice != null) {
            invoice.setStatus(status);
            return invoiceRepository.save(invoice);
        }
        return null;
    }

    public Invoice getById(UUID id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<Invoice> getByPatientId(UUID patientId) {
        return invoiceRepository.findByPatientId(patientId);
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }
}