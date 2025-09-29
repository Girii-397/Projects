package com.healthcare.hospitalintegration;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DataSyncService {

    @Autowired
    private PatientRepository patientRepository;

    @KafkaListener(topics = "hospital-patient-data", groupId = "healthcare-group")
    public void syncPatientData(String message) {
        // Parse message and sync patient data
        // e.g., update or insert patient records
        Patient patient = parsePatientFromMessage(message);
        patientRepository.save(patient);
    }

    private Patient parsePatientFromMessage(String message) {
        // Implement parsing logic (e.g., JSON to Patient)
        return new Patient();
    }

    public void batchSync(List<Patient> patients) {
        patientRepository.saveAll(patients);
    }
}