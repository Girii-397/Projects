package com.healthcare.hospital;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "diagnostics-service", url = "http://localhost:8083")  // Adjust URL as needed
public interface DiagnosticsClient {

    @GetMapping("/api/v1/diagnostics/labresults/patient/{patientId}")
    List<LabResultSummary> getLabResultsByPatient(@PathVariable UUID patientId);

    // Simple summary class
    class LabResultSummary {
        private UUID id;
        private String testName;
        private String status;
        // getters/setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getTestName() { return testName; }
        public void setTestName(String testName) { this.testName = testName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}