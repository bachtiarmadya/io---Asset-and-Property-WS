package io.id.app.util.rest.feature;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;
import io.id.app.support.registry.MetricsRegistry;

@Provider
public class MetricsFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(new InstrumentedResourceMethodApplicationListener(MetricsRegistry.getRegistry()));
        return true;
    }

}
