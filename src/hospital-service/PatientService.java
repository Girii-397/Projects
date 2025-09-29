package com.healthcare.hospital;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient register(Patient patient) {
        // Validation and biometric check logic
        return patientRepository.save(patient);
    }

    public Patient getById(UUID id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}