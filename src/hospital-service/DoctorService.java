package com.healthcare.hospital;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor register(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getById(UUID id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public void assignPatient(UUID doctorId, UUID patientId) {
        Doctor doctor = getById(doctorId);
        if (doctor != null && !doctor.getAssignedPatients().contains(patientId)) {
            doctor.getAssignedPatients().add(patientId);
            doctorRepository.save(doctor);
        }
    }
}