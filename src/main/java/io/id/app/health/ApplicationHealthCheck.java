package io.id.app.health;

import com.codahale.metrics.health.HealthCheck;

@Health(name = "Application")
public class ApplicationHealthCheck extends HealthCheck {

    public ApplicationHealthCheck() {
        // Empty constructor
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy("Application is Up and Running");
    }

}
