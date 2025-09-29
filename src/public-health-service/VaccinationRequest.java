package com.healthcare.publichealth;

import java.util.UUID;

public class VaccinationRequest {
    private UUID patientId;
    private String vaccineType;
    private String batchNumber;

    // Getters and setters
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public String getVaccineType() { return vaccineType; }
    public void setVaccineType(String vaccineType) { this.vaccineType = vaccineType; }
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
}