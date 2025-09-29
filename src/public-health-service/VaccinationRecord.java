package com.healthcare.publichealth;

import java.util.Date;
import java.util.UUID;

public class VaccinationRecord {
    private UUID id;
    private UUID patientId;
    private String vaccineType;
    private Date vaccinationDate;
    private String administeredBy;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public String getVaccineType() { return vaccineType; }
    public void setVaccineType(String vaccineType) { this.vaccineType = vaccineType; }
    public Date getVaccinationDate() { return vaccinationDate; }
    public void setVaccinationDate(Date vaccinationDate) { this.vaccinationDate = vaccinationDate; }
    public String getAdministeredBy() { return administeredBy; }
    public void setAdministeredBy(String administeredBy) { this.administeredBy = administeredBy; }
}