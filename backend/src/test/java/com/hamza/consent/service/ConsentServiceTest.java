package com.hamza.consent.service;

import com.hamza.consent.domain.*;
import com.hamza.consent.repository.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsentServiceTest {
    @Test void rejectsMissingActorOrReason() {
        var records = mock(ConsentRecordRepository.class);
        var audit = mock(AuditEventRepository.class);
        var service = new ConsentService(records, audit);
        assertThrows(IllegalArgumentException.class, () -> service.record(UUID.randomUUID(),
                ConsentPurpose.MARKETING, ConsentStatus.GIVEN, "", "test"));
        verifyNoInteractions(records, audit);
    }
    @Test void createsFirstVersion() {
        var records = mock(ConsentRecordRepository.class);
        var audit = mock(AuditEventRepository.class);
        when(records.findTopByCustomerIdAndPurposeOrderByVersionDesc(any(), any())).thenReturn(Optional.empty());
        when(records.save(any())).thenAnswer(i -> i.getArgument(0));
        var service = new ConsentService(records, audit);
        var result = service.record(UUID.randomUUID(), ConsentPurpose.PROFILING, ConsentStatus.GIVEN, "customer", "explicit choice");
        assertEquals(1, result.getVersion());
        verify(audit).save(any(AuditEvent.class));
    }
}
