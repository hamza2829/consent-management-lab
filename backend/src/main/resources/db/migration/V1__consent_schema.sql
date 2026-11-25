CREATE TABLE consent_records (
  id UUID PRIMARY KEY, customer_id UUID NOT NULL, purpose VARCHAR(40) NOT NULL,
  status VARCHAR(20) NOT NULL, version INTEGER NOT NULL, occurred_at TIMESTAMPTZ NOT NULL,
  actor VARCHAR(120) NOT NULL, reason VARCHAR(500) NOT NULL,
  CONSTRAINT uq_consent_version UNIQUE(customer_id, purpose, version)
);
CREATE INDEX ix_consent_customer ON consent_records(customer_id, occurred_at DESC);
CREATE TABLE audit_events (
  id UUID PRIMARY KEY, customer_id UUID NOT NULL, action VARCHAR(80) NOT NULL,
  actor VARCHAR(120) NOT NULL, reason VARCHAR(500) NOT NULL, occurred_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX ix_audit_customer ON audit_events(customer_id, occurred_at DESC);
