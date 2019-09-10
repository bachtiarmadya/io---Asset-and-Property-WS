package io.id.app.manager;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class RestServiceManager extends ResourceConfig {

    public RestServiceManager() {

        // Packages to Auto Discover
        packages("io.id.app.rest", "io.id.app.support.rest.service", "io.id.app.util.rest, io.id.mobile.service");
    }
}
