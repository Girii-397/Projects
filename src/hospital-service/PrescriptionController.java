package com.healthcare.hospital;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hospital/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public Prescription prescribe(@RequestBody Prescription prescription) {
        return prescriptionService.prescribe(prescription);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT') or hasRole('HOSPITAL')")
    public Prescription getPrescription(@PathVariable UUID id) {
        return prescriptionService.getById(id);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT') or hasRole('HOSPITAL')")
    public List<Prescription> getPrescriptionsByPatient(@PathVariable UUID patientId) {
        return prescriptionService.getByPatientId(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Prescription> getPrescriptionsByDoctor(@PathVariable UUID doctorId) {
        return prescriptionService.getByDoctorId(doctorId);
    }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAll();
    }
}