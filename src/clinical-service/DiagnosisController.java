package com.healthcare.clinical;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/clinical")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping("/diagnose")
    public DiagnosisResponse diagnose(@RequestBody DiagnosisRequest request) {
        return diagnosisService.performDiagnosis(request);
    }

    @GetMapping("/recommendations/{patientId}")
    public RecommendationResponse getRecommendations(@PathVariable UUID patientId) {
        return diagnosisService.getRecommendations(patientId);
    }
}