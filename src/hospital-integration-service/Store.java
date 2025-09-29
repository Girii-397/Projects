package com.healthcare.hospitalintegration;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID hospitalId;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private StoreType type;

    public enum StoreType {
        PHARMACY, LAB, DIAGNOSTIC
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getHospitalId() { return hospitalId; }
    public void setHospitalId(UUID hospitalId) { this.hospitalId = hospitalId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public StoreType getType() { return type; }
    public void setType(StoreType type) { this.type = type; }
}