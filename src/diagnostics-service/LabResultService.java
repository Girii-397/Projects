package com.healthcare.diagnostics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class LabResultService {

    @Autowired
    private LabResultRepository labResultRepository;

    @Autowired
    private AuditService auditService;

    public LabResult addLabResult(LabResult labResult) {
        labResult.setTestDate(new java.util.Date());
        LabResult saved = labResultRepository.save(labResult);
        auditService.logAudit("LabResult", saved.getId(), "CREATE", null, "Result added");
        return saved;
    }

    public LabResult approveResult(UUID resultId, UUID userId) {
        LabResult labResult = labResultRepository.findById(resultId).orElseThrow(() -> new RuntimeException("Result not found"));
        labResult.setApproved(true);
        labResult.setApprovedBy(userId);
        labResult.setApprovedDate(new java.util.Date());
        LabResult saved = labResultRepository.save(labResult);
        auditService.logAudit("LabResult", resultId, "APPROVE", userId, "Result approved");
        return saved;
    }
}