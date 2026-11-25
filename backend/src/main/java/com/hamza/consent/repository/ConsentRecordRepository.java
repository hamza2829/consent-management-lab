package com.hamza.consent.repository;
import com.hamza.consent.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ConsentRecordRepository extends JpaRepository<ConsentRecord, UUID> {
    List<ConsentRecord> findByCustomerIdOrderByOccurredAtDesc(UUID customerId);
    Optional<ConsentRecord> findTopByCustomerIdAndPurposeOrderByVersionDesc(UUID customerId, ConsentPurpose purpose);
}
