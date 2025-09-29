package com.healthcare.hospital;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID patientId;
    private UUID doctorId;
    private String medication;
    private String dosage;
    private Date issuedDate;
    private String notes;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public UUID getDoctorId() { return doctorId; }
    public void setDoctorId(UUID doctorId) { this.doctorId = doctorId; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public Date getIssuedDate() { return issuedDate; }
    public void setIssuedDate(Date issuedDate) { this.issuedDate = issuedDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}