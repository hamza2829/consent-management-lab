package com.hamza.consent.repository;
import com.hamza.consent.domain.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AuditEventRepository extends JpaRepository<AuditEvent, UUID> {
    List<AuditEvent> findByCustomerIdOrderByOccurredAtDesc(UUID customerId);
}
