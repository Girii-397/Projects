package com.healthcare.hospital;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String specialty;
    private String availability; // e.g., "Mon-Fri 9-5"
    @ElementCollection
    private List<UUID> assignedPatients;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public List<UUID> getAssignedPatients() { return assignedPatients; }
    public void setAssignedPatients(List<UUID> assignedPatients) { this.assignedPatients = assignedPatients; }
}