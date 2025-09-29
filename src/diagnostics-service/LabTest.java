package com.healthcare.diagnostics;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "lab_tests")
public class LabTest {

    public enum Priority {
        ROUTINE, URGENT, STAT
    }

    @Id
    @GeneratedValue
    private UUID id;

    private UUID patientId;
    private String testName;
    private String status;
    private Date orderDate;
    private Priority priority;
    private Date completedDate;

    @Version
    private Long version;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Date getCompletedDate() { return completedDate; }
    public void setCompletedDate(Date completedDate) { this.completedDate = completedDate; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}