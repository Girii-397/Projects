package com.healthcare.diagnostics;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue
    private UUID id;

    private String entityType;
    private UUID entityId;
    private String action;
    private UUID userId;
    private Date timestamp;
    private String details;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }
    public UUID getEntityId() { return entityId; }
    public void setEntityId(UUID entityId) { this.entityId = entityId; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}