package io.id.app.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.codahale.metrics.annotation.Timed;
import io.id.app.rest.model.SampleRequest;
import io.id.app.util.json.JsonHelper;
import io.id.app.util.rest.model.ServiceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleService extends BaseService {

    public SampleService() {
        log = getLogger(this.getClass());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @ApiOperation(value = "Create Sample Request",
            notes = "Multiple status values can be provided with comma seperated strings", response = Response.class)
    public Response post(@ApiParam(required = true) SampleRequest request) {
        log.info("request : " + JsonHelper.toJson(request));
        ServiceResponse response = new ServiceResponse(Status.OK,
                "POST Hello World : " + getConfig("sample") + " : " + getConfig("JAVA_HOME") + " : ");
        return buildResponse(Status.OK, response);
    }

    @GET
    @Timed
    public Response get() {
        ServiceResponse response = new ServiceResponse(Status.OK,
                "GET Hello World : " + getConfig("sample") + " : " + getConfig("JAVA_HOME") + " : ");
        return buildResponse(Status.OK, response);
    }

}
