package com.healthcare.hospitalintegration;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthcare.hospital.Patient;
import com.healthcare.hospital.PatientRepository;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DataSyncService {

    private static final Logger logger = LoggerFactory.getLogger(DataSyncService.class);

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[1-9]\\d{1,14}$");
    private static final Pattern NATIONAL_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]{5,20}$");

    @Autowired
    private PatientRepository patientRepository;

    @KafkaListener(topics = "hospital-patient-data", groupId = "healthcare-group")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void syncPatientData(String message) {
        logger.info("Received patient data message for syncing");
        Patient patient = parsePatientFromMessage(message);
        if (patient != null) {
            try {
                patientRepository.save(patient);
                logger.info("Successfully synced patient with ID: {}", patient.getId());
            } catch (Exception e) {
                logger.error("Failed to save patient to database: {}", patient, e);
                throw e; // Re-throw to allow Kafka error handling
            }
        } else {
            logger.warn("Skipping sync due to invalid patient data in message");
        }
    }

    private Patient parsePatientFromMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            logger.warn("Received null or empty message for patient parsing");
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            Patient patient = mapper.readValue(message, Patient.class);

            if (!validatePatient(patient)) {
                logger.warn("Patient data validation failed for message: {}", message);
                return null;
            }

            logger.debug("Successfully parsed and validated patient from message");
            return patient;
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse patient message as JSON: {}", message, e);
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error during patient message parsing: {}", message, e);
            return null;
        }
    }

    private boolean validatePatient(Patient patient) {
        if (patient == null) {
            logger.warn("Patient is null");
            return false;
        }

        // Name validation
        String name = patient.getName();
        if (name == null || name.trim().isEmpty() || name.length() > 100) {
            logger.warn("Invalid patient name: {}", name);
            return false;
        }

        // DOB validation
        Date dob = patient.getDob();
        if (dob == null) {
            logger.warn("Patient DOB is null");
            return false;
        }
        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        if (birthDate.isAfter(now)) {
            logger.warn("Patient DOB is in the future: {}", dob);
            return false;
        }
        int age = Period.between(birthDate, now).getYears();
        if (age < 0 || age > 150) {
            logger.warn("Invalid patient age: {}", age);
            return false;
        }

        // ContactInfo validation (optional)
        String contact = patient.getContactInfo();
        if (contact != null && !contact.trim().isEmpty()) {
            if (!EMAIL_PATTERN.matcher(contact).matches() && !PHONE_PATTERN.matcher(contact).matches()) {
                logger.warn("Invalid contact info format: {}", contact);
                return false;
            }
        }

        // NationalId validation (optional)
        String nationalId = patient.getNationalId();
        if (nationalId != null && !nationalId.trim().isEmpty()) {
            if (!NATIONAL_ID_PATTERN.matcher(nationalId).matches()) {
                logger.warn("Invalid national ID format: {}", nationalId);
                return false;
            }
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void batchSync(List<Patient> patients) {
        if (patients == null || patients.isEmpty()) {
            logger.warn("No patients provided for batch sync");
            return;
        }
        logger.info("Starting batch sync for {} patients", patients.size());

        List<Patient> validPatients = patients.stream()
            .filter(this::validatePatient)
            .collect(Collectors.toList());

        int invalidCount = patients.size() - validPatients.size();
        if (invalidCount > 0) {
            logger.warn("Filtered out {} invalid patients from batch sync", invalidCount);
        }

        try {
            patientRepository.saveAll(validPatients);
            logger.info("Successfully batch synced {} patients", validPatients.size());
        } catch (Exception e) {
            logger.error("Failed to batch sync patients", e);
            throw e; // Re-throw for caller handling
        }
    }
}