package com.hamza.consent.api;

import com.hamza.consent.domain.*;
import com.hamza.consent.service.ConsentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/customers/{customerId}")
public class ConsentController {
    private final ConsentService service;
    public ConsentController(ConsentService service) { this.service = service; }

    public record ConsentCommand(ConsentPurpose purpose, @NotBlank String actor, @NotBlank String reason) {}
    @PostMapping("/consents")
    @Operation(summary = "Give consent")
    public ConsentRecord give(@PathVariable UUID customerId, @Valid @RequestBody ConsentCommand c) {
        return service.record(customerId, c.purpose(), ConsentStatus.GIVEN, c.actor(), c.reason());
    }
    @PutMapping("/consents")
    @Operation(summary = "Change consent")
    public ConsentRecord change(@PathVariable UUID customerId, @Valid @RequestBody ConsentCommand c) {
        return service.record(customerId, c.purpose(), ConsentStatus.CHANGED, c.actor(), c.reason());
    }
    @DeleteMapping("/consents/{purpose}")
    @Operation(summary = "Withdraw consent")
    public ConsentRecord withdraw(@PathVariable UUID customerId, @PathVariable ConsentPurpose purpose,
                                  @RequestParam String actor, @RequestParam String reason) {
        return service.record(customerId, purpose, ConsentStatus.WITHDRAWN, actor, reason);
    }
    @GetMapping("/consents")
    @Operation(summary = "List consent history")
    public List<ConsentRecord> list(@PathVariable UUID customerId) { return service.list(customerId); }
    @GetMapping("/audit")
    @Operation(summary = "Query append-only audit trail")
    public List<AuditEvent> audit(@PathVariable UUID customerId) { return service.history(customerId); }
    @GetMapping("/gdpr/export")
    @Operation(summary = "Export customer data as JSON")
    public Map<String,Object> export(@PathVariable UUID customerId) {
        return Map.of("customerId", customerId, "consents", service.list(customerId), "audit", service.history(customerId));
    }
}
