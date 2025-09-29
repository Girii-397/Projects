package com.healthcare.insurance;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID patientId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Date date;
    @ElementCollection
    private List<String> items; // e.g., list of services billed

    public enum PaymentStatus {
        PENDING, PAID
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
}