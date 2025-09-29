package com.healthcare.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    List<Invoice> findByPatientId(UUID patientId);
}