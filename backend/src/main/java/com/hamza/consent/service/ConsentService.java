package com.hamza.consent.service;

import com.hamza.consent.domain.*;
import com.hamza.consent.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ConsentService {
    private final ConsentRecordRepository records;
    private final AuditEventRepository audit;
    public ConsentService(ConsentRecordRepository records, AuditEventRepository audit) {
        this.records = records; this.audit = audit;
    }
    @Transactional
    public ConsentRecord record(UUID customerId, ConsentPurpose purpose, ConsentStatus status, String actor, String reason) {
        Objects.requireNonNull(customerId); Objects.requireNonNull(purpose); Objects.requireNonNull(status);
        if (actor == null || actor.isBlank() || reason == null || reason.isBlank())
            throw new IllegalArgumentException("Actor and reason are required");
        int version = records.findTopByCustomerIdAndPurposeOrderByVersionDesc(customerId, purpose)
                .map(r -> r.getVersion() + 1).orElse(1);
        ConsentRecord saved = records.save(new ConsentRecord(customerId, purpose, status, version, actor, reason));
        audit.save(new AuditEvent(customerId, status.name(), actor, reason));
        return saved;
    }
    @Transactional(readOnly = true)
    public List<ConsentRecord> list(UUID customerId) {
        return records.findByCustomerIdOrderByOccurredAtDesc(customerId);
    }
    @Transactional(readOnly = true)
    public List<AuditEvent> history(UUID customerId) {
        return audit.findByCustomerIdOrderByOccurredAtDesc(customerId);
    }
}
