package io.id.app.support.registry;

import org.atteo.classindex.ClassIndex;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import io.id.app.health.Health;
import io.id.app.manager.BaseManager;

public class HealthRegistry extends BaseManager {
    private static HealthCheckRegistry registry;

    private static void init() {
        log = getLogger(HealthRegistry.class);

        registry = new HealthCheckRegistry();

        ClassIndex.getAnnotated(Health.class).forEach(clazz -> {
            try {

                // Get Annotation
                Health health = clazz.getAnnotation(Health.class);

                // Register to registry
                registry.register(health.name(), (HealthCheck) clazz.newInstance());

            } catch (Exception ex) {
                log.error("init", ex);
            }
        });
    }

    public static HealthCheckRegistry getRegistry() {
        if (registry == null) {
            init();
        }

        return registry;
    }

}
