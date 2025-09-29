package com.healthcare.pharmacy;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/pharmacy")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/prescription")
    public Prescription issuePrescription(@RequestBody PrescriptionRequest request) {
        return prescriptionService.issue(request);
    }

    @GetMapping("/inventory/{medication}")
    public Inventory checkInventory(@PathVariable String medication) {
        return prescriptionService.getInventory(medication);
    }
}