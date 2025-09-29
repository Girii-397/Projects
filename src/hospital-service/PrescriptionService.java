package com.healthcare.hospital;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription prescribe(Prescription prescription) {
        prescription.setIssuedDate(new Date());
        return prescriptionRepository.save(prescription);
    }

    public Prescription getById(UUID id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    public List<Prescription> getByPatientId(UUID patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    public List<Prescription> getByDoctorId(UUID doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }

    public List<Prescription> getAll() {
        return prescriptionRepository.findAll();
    }
}