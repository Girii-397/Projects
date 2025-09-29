package com.healthcare.hospital;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private Date dob;
    private String gender;
    private String address;
    private String contactInfo; // Encrypted
    private String nationalId; // Encrypted
    private String emergencyContact;
    private List<String> medicalHistory;
    private String qrCode; // Base64 encoded QR code for patient ID

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public List<String> getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(List<String> medicalHistory) { this.medicalHistory = medicalHistory; }
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}