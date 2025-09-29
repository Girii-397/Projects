package com.healthcare.emergency;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/emergency")
public class TriageController {

    @Autowired
    private TriageService triageService;

    @PostMapping("/triage")
    public TriageResponse performTriage(@RequestBody TriageRequest request) {
        return triageService.triage(request);
    }

    @GetMapping("/icu/{patientId}")
    public ICUMonitoring getICUData(@PathVariable UUID patientId) {
        return triageService.getICUData(patientId);
    }
}