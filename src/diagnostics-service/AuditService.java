package com.healthcare.diagnostics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logAudit(String entityType, UUID entityId, String action, UUID userId, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityType(entityType);
        auditLog.setEntityId(entityId);
        auditLog.setAction(action);
        auditLog.setUserId(userId);
        auditLog.setTimestamp(new java.util.Date());
        auditLog.setDetails(details);
        auditLogRepository.save(auditLog);
    }

    public List<AuditLog> getAuditLogs(String entityType, UUID entityId) {
        return auditLogRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }
}