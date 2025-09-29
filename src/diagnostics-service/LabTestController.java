package com.healthcare.diagnostics;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/diagnostics")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private LabResultRepository labResultRepository;

    @Autowired
    private LabResultService labResultService;

    @Autowired
    private AuditService auditService;

    @PostMapping("/labtest")
    public LabTest orderTest(@RequestBody LabTestRequest request) {
        return labTestService.orderTest(request);
    }

    @GetMapping("/results/{testId}")
    public LabTest getResults(@PathVariable UUID testId) {
        return labTestService.getResults(testId);
    }

    @PostMapping("/labresult")
    public LabResult addLabResult(@RequestBody LabResult labResult) {
        return labResultService.addLabResult(labResult);
    }

    @PutMapping("/labtest/{id}/status")
    public LabTest updateTestStatus(@PathVariable UUID id, @RequestBody StatusUpdateRequest request) {
        return labTestService.updateStatus(id, request.getStatus());
    }

    @PutMapping("/labresult/{id}/approve")
    public LabResult approveLabResult(@PathVariable UUID id) {
        // Assume userId from context, placeholder
        return labResultService.approveResult(id, UUID.randomUUID());
    }

    @GetMapping("/audit/{entityType}/{entityId}")
    public List<AuditLog> getAuditLogs(@PathVariable String entityType, @PathVariable UUID entityId) {
        return auditService.getAuditLogs(entityType, entityId);
    }

    @GetMapping("/labresults/patient/{patientId}")
    public List<LabResult> getLabResultsByPatient(@PathVariable UUID patientId) {
        return labResultRepository.findByPatientId(patientId);
    }
}

class StatusUpdateRequest {
    private String status;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}