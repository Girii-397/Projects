package com.healthcare.diagnostics;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "lab_results")
public class LabResult {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID patientId;
    private String testName;
    private String result;
    private Date testDate;
    private boolean approved;
    private UUID approvedBy;
    private Date approvedDate;

    @Version
    private Long version;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public Date getTestDate() { return testDate; }
    public void setTestDate(Date testDate) { this.testDate = testDate; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
    public UUID getApprovedBy() { return approvedBy; }
    public void setApprovedBy(UUID approvedBy) { this.approvedBy = approvedBy; }
    public Date getApprovedDate() { return approvedDate; }
    public void setApprovedDate(Date approvedDate) { this.approvedDate = approvedDate; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}