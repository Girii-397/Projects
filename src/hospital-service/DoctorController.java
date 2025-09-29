package com.healthcare.hospital;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hospital/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @PreAuthorize("hasRole('HOSPITAL')")
    public Doctor registerDoctor(@RequestBody Doctor doctor) {
        return doctorService.register(doctor);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public Doctor getDoctor(@PathVariable UUID id) {
        return doctorService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAll();
    }

    @PostMapping("/{doctorId}/assign/{patientId}")
    @PreAuthorize("hasRole('HOSPITAL')")
    public void assignPatient(@PathVariable UUID doctorId, @PathVariable UUID patientId) {
        doctorService.assignPatient(doctorId, patientId);
    }
}