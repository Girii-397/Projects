package com.healthcare.hospital;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient registerPatient(@RequestBody Patient patient) {
        return patientService.register(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable UUID id) {
        return patientService.getById(id);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAll();
    }
}