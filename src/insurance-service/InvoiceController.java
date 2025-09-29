package com.healthcare.insurance;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/insurance/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate")
    @PreAuthorize("hasRole('HOSPITAL') or hasRole('INSURANCE')")
    public Invoice generateInvoice(@RequestParam UUID patientId, @RequestParam BigDecimal amount, @RequestBody List<String> items) {
        return invoiceService.generateInvoice(patientId, amount, items);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('PATIENT') or hasRole('INSURANCE')")
    public Invoice updatePaymentStatus(@PathVariable UUID id, @RequestParam Invoice.PaymentStatus status) {
        return invoiceService.updatePaymentStatus(id, status);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('HOSPITAL') or hasRole('INSURANCE')")
    public Invoice getInvoice(@PathVariable UUID id) {
        return invoiceService.getById(id);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('HOSPITAL') or hasRole('INSURANCE')")
    public List<Invoice> getInvoicesByPatient(@PathVariable UUID patientId) {
        return invoiceService.getByPatientId(patientId);
    }

    @GetMapping
    @PreAuthorize("hasRole('HOSPITAL') or hasRole('INSURANCE')")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAll();
    }
}