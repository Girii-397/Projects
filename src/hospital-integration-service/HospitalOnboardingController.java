package com.healthcare.hospitalintegration;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/hospital-integration")
public class HospitalOnboardingController {

    @Autowired
    private HospitalOnboardingService onboardingService;

    @PostMapping("/onboard")
    public Hospital registerHospital(@RequestBody Hospital hospital) {
        return onboardingService.onboard(hospital);
    }

    @PostMapping("/sync/patient-data")
    public SyncResponse syncPatientData(@RequestBody SyncRequest request) {
        return onboardingService.syncData(request);
    }

    @GetMapping("/compliance/check/{hospitalId}")
    public ComplianceReport checkCompliance(@PathVariable UUID hospitalId) {
        return onboardingService.checkCompliance(hospitalId);
    }

    @PostMapping("/stores")
    public Store addStore(@RequestBody Store store) {
        return onboardingService.addStore(store);
    }

    @GetMapping("/stores/{hospitalId}")
    public java.util.List<Store> getStoresByHospital(@PathVariable UUID hospitalId) {
        return onboardingService.getStoresByHospital(hospitalId);
    }
}