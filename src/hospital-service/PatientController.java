package com.healthcare.hospital;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hospital/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    @PreAuthorize("hasRole('HOSPITAL')")
    public Patient registerPatient(@RequestBody Patient patient) {
        return patientService.register(patient);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public Patient getPatient(@PathVariable UUID id) {
        return patientService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Patient> getAllPatients() {
        return patientService.getAll();
    }
}