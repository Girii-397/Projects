package com.healthcare.hospital;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/hospital/patients")
public class PatientStatusController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private com.healthcare.diagnostics.LabResultRepository labResultRepository;

    @Autowired
    private com.healthcare.insurance.InvoiceService invoiceService;

    @GetMapping("/{id}/status")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public Map<String, Object> getPatientStatus(@PathVariable UUID id) {
        Map<String, Object> status = new HashMap<>();
        status.put("patient", patientService.getById(id));
        status.put("appointments", appointmentService.getByPatientId(id));
        status.put("prescriptions", prescriptionService.getByPatientId(id));
        status.put("labResults", labResultRepository.findByPatientId(id));
        status.put("invoices", invoiceService.getByPatientId(id));
        return status;
    }
}