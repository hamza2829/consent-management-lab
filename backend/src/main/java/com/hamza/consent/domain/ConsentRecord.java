package com.hamza.consent.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "consent_records")
public class ConsentRecord {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false) private UUID customerId;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private ConsentPurpose purpose;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private ConsentStatus status;
    @Column(nullable = false) private int version;
    @Column(nullable = false) private Instant occurredAt;
    @Column(nullable = false) private String actor;
    @Column(nullable = false, length = 500) private String reason;

    protected ConsentRecord() {}
    public ConsentRecord(UUID customerId, ConsentPurpose purpose, ConsentStatus status, int version, String actor, String reason) {
        this.customerId = customerId; this.purpose = purpose; this.status = status;
        this.version = version; this.actor = actor; this.reason = reason; this.occurredAt = Instant.now();
    }
    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public ConsentPurpose getPurpose() { return purpose; }
    public ConsentStatus getStatus() { return status; }
    public int getVersion() { return version; }
    public Instant getOccurredAt() { return occurredAt; }
    public String getActor() { return actor; }
    public String getReason() { return reason; }
}
