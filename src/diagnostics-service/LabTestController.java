package com.healthcare.diagnostics;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/diagnostics")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @PostMapping("/labtest")
    public LabTest orderTest(@RequestBody LabTestRequest request) {
        return labTestService.orderTest(request);
    }

    @GetMapping("/results/{testId}")
    public LabTest getResults(@PathVariable UUID testId) {
        return labTestService.getResults(testId);
    }
}