package io.id.app.support.rest.service;

import java.util.Map;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.Timer;
import io.id.app.support.registry.MetricsRegistry;
import io.swagger.annotations.Api;

@Api
@Path("metrics")
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("rawtypes")
public class MetricsService {

    @GET
    @Path("threads")
    public Map<String, Gauge> getThreads() {
        return MetricsRegistry.getRegistry().getGauges(MetricFilter.startsWith("threads"));
    }

    @GET
    @Path("memory")
    public Map<String, Gauge> getMemory() {
        return MetricsRegistry.getRegistry().getGauges(MetricFilter.startsWith("memory"));
    }

    @GET
    @Path("gc")
    public Map<String, Gauge> getGC() {
        return MetricsRegistry.getRegistry().getGauges(MetricFilter.startsWith("gc"));
    }

    @GET
    @Path("/histogram")
    public Map<String, Histogram> getHistogram() {
        return MetricsRegistry.getRegistry().getHistograms();
    }

    @GET
    @Path("/timers")
    public Set<String> getTimerList() {
        return MetricsRegistry.getRegistry().getTimers().keySet();
    }


    @GET
    @Path("/timers/{serviceClass}")
    public Map<String, Timer> getService(@PathParam("serviceClass") String serviceClass) {
        return MetricsRegistry.getRegistry().getTimers(MetricFilter.contains(serviceClass));
    }
}
