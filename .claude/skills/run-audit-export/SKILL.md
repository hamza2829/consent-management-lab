# Run an audit export
1. Verify the caller has the auditor role.
2. Query events in stable timestamp order and paginate large exports.
3. Export JSON with event timestamp, actor, action and reason.
4. Record export execution metadata without mutating source audit rows.
5. Use synthetic fixtures only; do not export production data from this lab.
