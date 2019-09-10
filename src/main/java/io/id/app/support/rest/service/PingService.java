package io.id.app.support.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@Path("ping")
public class PingService {

    @GET
    @ApiOperation(value = "Ping Service", notes = "Checks if the service is up", response = String.class)
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Pong";
    }
}
