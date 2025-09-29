package com.healthcare.diagnostics;

import java.util.UUID;

public class LabTestRequest {
    private UUID patientId;
    private String testName;
    private LabTest.Priority priority;

    // Getters and setters
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    public LabTest.Priority getPriority() { return priority; }
    public void setPriority(LabTest.Priority priority) { this.priority = priority; }
}