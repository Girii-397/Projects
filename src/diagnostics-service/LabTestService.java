package com.healthcare.diagnostics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private AuditService auditService;

    public LabTest orderTest(LabTestRequest request) {
        LabTest labTest = new LabTest();
        labTest.setPatientId(request.getPatientId());
        labTest.setTestName(request.getTestName());
        labTest.setStatus("ordered");
        labTest.setOrderDate(new java.util.Date());
        labTest.setPriority(request.getPriority() != null ? request.getPriority() : LabTest.Priority.ROUTINE);
        LabTest saved = labTestRepository.save(labTest);
        auditService.logAudit("LabTest", saved.getId(), "CREATE", null, "Test ordered");
        return saved;
    }

    public LabTest updateStatus(UUID testId, String status) {
        LabTest labTest = labTestRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        String oldStatus = labTest.getStatus();
        labTest.setStatus(status);
        if ("completed".equals(status)) {
            labTest.setCompletedDate(new java.util.Date());
        }
        LabTest saved = labTestRepository.save(labTest);
        auditService.logAudit("LabTest", testId, "UPDATE", null, "Status changed from " + oldStatus + " to " + status);
        return saved;
    }

    public LabTest getResults(UUID testId) {
        return labTestRepository.findById(testId).orElse(null);
    }
}