package com.healthcare.hospitalintegration;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String address;
    private String contactInfo;
    private Date registrationDate;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    public enum RegistrationStatus {
        PENDING, ACTIVE, SUSPENDED
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
}