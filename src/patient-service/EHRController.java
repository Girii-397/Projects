package com.healthcare.patient;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/patient/ehr")
public class EHRController {

    @Autowired
    private EHRService ehrService;

    @GetMapping("/{patientId}")
    public EHRRecord getEHR(@PathVariable UUID patientId) {
        return ehrService.getEHR(patientId);
    }

    @PostMapping("/update")
    public void updateEHR(@RequestBody EHRUpdateRequest request) {
        ehrService.updateEHR(request);
    }
}