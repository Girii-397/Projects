package com.healthcare.publichealth;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/publichealth")
public class OutbreakController {

    @Autowired
    private OutbreakService outbreakService;

    @GetMapping("/outbreaks")
    public OutbreakData getOutbreaks() {
        return outbreakService.getOutbreakData();
    }

    @PostMapping("/vaccination")
    public VaccinationRecord recordVaccination(@RequestBody VaccinationRequest request) {
        return outbreakService.recordVaccination(request);
    }
}