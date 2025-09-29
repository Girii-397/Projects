package com.healthcare.monitoring;

import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class PerformanceMonitor {

    private final MeterRegistry meterRegistry;

    public PerformanceMonitor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void recordAuditLogQueryTime(long timeMs) {
        meterRegistry.timer("audit.log.query.time").record(timeMs, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    public void checkBenchmark() {
        // Ensure <20ms for audit logs
        // Implement alerting if exceeded
    }
}