package io.id.app.manager;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/")
public class DocServiceManager extends ResourceConfig {

    public DocServiceManager() {

        packages("io.swagger.jaxrs.listing");

        BeanConfig config = new BeanConfig();
        config.setConfigId("spring-boot-jaxrs-swagger");
        config.setTitle("Spring boot jaxrs swagger integration");
        config.setVersion("v1");
        config.setBasePath("http://localhost:8080/api");
        config.setResourcePackage("io.id.app.rest.service,io.id.app.support.rest.service");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

}
