package io.id.app.support.rest.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import io.id.app.support.registry.HealthRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api
@Path("health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthService {

    @GET
    public Map<String, HealthCheck.Result> runHealthChecks() {
        return getRegistry().runHealthChecks();
    }

    @GET
    @Path("{name}")
    public Map<String, HealthCheck.Result> runHealthChecks(@ApiParam(name = "name") @PathParam("name") String name) {
        Set<String> healthCheckSet = getRegistry().getNames();
        Map<String, HealthCheck.Result> resultMap = new HashMap<>();

        healthCheckSet.forEach(checkName -> {
            if (checkName.contains(name)) {
                resultMap.put(checkName, getRegistry().runHealthCheck(checkName));
            }
        });
        return resultMap;
    }

    private HealthCheckRegistry getRegistry() {
        return HealthRegistry.getRegistry();
    }

}
