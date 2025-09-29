package com.healthcare.insurance;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/insurance")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping("/claim")
    public InsuranceClaim submitClaim(@RequestBody ClaimRequest request) {
        return claimService.submitClaim(request);
    }

    @GetMapping("/reimbursement/{claimId}")
    public Reimbursement getReimbursement(@PathVariable UUID claimId) {
        return claimService.getReimbursement(claimId);
    }
}