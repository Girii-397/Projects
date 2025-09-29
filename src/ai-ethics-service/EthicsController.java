package com.healthcare.ethics;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/ethics")
public class EthicsController {

    @Autowired
    private EthicsService ethicsService;

    @PostMapping("/audit")
    public EthicsAudit runAudit(@RequestBody AuditRequest request) {
        return ethicsService.auditModel(request);
    }

    @GetMapping("/audits/{modelId}")
    public List<EthicsAudit> getAudits(@PathVariable UUID modelId) {
        return ethicsService.getAuditHistory(modelId);
    }
}