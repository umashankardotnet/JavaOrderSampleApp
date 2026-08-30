# Order Processor — deliberately vulnerable sample app

> ⚠️ **This application intentionally pins a known-vulnerable dependency and
> logs untrusted input.** It exists only as a remediation target for the AWS
> Transform Continuous Modernization workshop. **Do not deploy it anywhere.**

A tiny single-endpoint HTTP service (JDK built-in `HttpServer`, **no web
framework**) that gives AWS Transform's **`security` analysis** and **AI
remediation agent** exactly one real finding to detect, remediate, and
document. Using the JDK server means there are **no transitive dependencies**
to scan, so the analysis stays fast and the finding count is deterministic.

## Planted vulnerability

| Dependency | Version | CVE(s) | Class |
|------------|---------|--------|-------|
| `log4j-core` / `log4j-api` | 2.14.1 | CVE-2021-44228 (Log4Shell), CVE-2021-45046 | Remote code execution |

**Reachable in code** (see `OrderController`):

- `GET /orders/note?value=...` logs the caller-supplied `value` on vulnerable
  log4j → Log4Shell (`${jndi:ldap://...}` evaluation).

## Build & test locally (optional)

You do **not** need to run this locally for the workshop — AWS Transform builds
it for you. But if you want to verify the baseline:

```bash
cd sample-app
mvn -q clean test
```

## Expected remediation outcome

After the Security Agent runs, expect a pull request that:

1. Bumps `log4j` to a fixed 2.17.x+ line.
2. Keeps `mvn test` green.
3. Includes a written summary of the finding, the fix, and residual risk.
