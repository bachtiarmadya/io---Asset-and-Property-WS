package io.id.app.support.registry;

import java.util.concurrent.TimeUnit;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.CachedThreadStatesGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;

public class MetricsRegistry {

    private static MetricRegistry registry;

    private MetricsRegistry() {
        // Empty Constructor
    }

    private static void init() {
        registry = new MetricRegistry();
        registry.register("gc", new GarbageCollectorMetricSet());
        registry.register("threads", new CachedThreadStatesGaugeSet(10, TimeUnit.SECONDS));
        registry.register("memory", new MemoryUsageGaugeSet());
    }

    public static MetricRegistry getRegistry() {
        if (registry == null) {
            init();
        }
        return registry;
    }
}
