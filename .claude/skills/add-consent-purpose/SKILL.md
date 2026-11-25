# Add a consent purpose
1. Add the enum value to `ConsentPurpose`.
2. Add a Flyway migration only if the persistence model requires a schema change.
3. Add API and service tests for lifecycle transitions.
4. Update the portal labels and README domain description.
5. Run backend and frontend tests; never rewrite historical audit events.
