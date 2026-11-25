package com.hamza.consent.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_events")
public class AuditEvent {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(nullable = false) private UUID customerId;
    @Column(nullable = false, length = 80) private String action;
    @Column(nullable = false, length = 120) private String actor;
    @Column(nullable = false, length = 500) private String reason;
    @Column(nullable = false, updatable = false) private Instant occurredAt = Instant.now();
    protected AuditEvent() {}
    public AuditEvent(UUID customerId, String action, String actor, String reason) {
        this.customerId = customerId; this.action = action; this.actor = actor; this.reason = reason;
    }
    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public String getAction() { return action; }
    public String getActor() { return actor; }
    public String getReason() { return reason; }
    public Instant getOccurredAt() { return occurredAt; }
}
