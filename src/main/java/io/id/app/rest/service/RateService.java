/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import io.id.app.controller.RateController;
import io.id.app.model.ProvinceModel;
import io.id.app.model.RateModel;
import io.id.app.request.AddRateRequest;
import io.id.app.request.EditRateRequest;
import io.id.app.rest.model.ServerResponse;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("rate")
@Produces(MediaType.APPLICATION_JSON)
public class RateService extends BaseService {

    private RateController rateController;

    public RateService() {
        log = getLogger(this.getClass());
        this.rateController = new RateController();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AddRateRequest input) {
        Response res = null;
        boolean isCreate = rateController.create(input.getRatename(), input.getValue());
        if (isCreate) {
            ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
            res = Response.status(Response.Status.OK).entity(serverResponse).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
            res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
        }
        return res;
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(EditRateRequest input) {
        Response res = null;
        boolean isUpdate = rateController.edit(input.getRateid(), input.getRatename(), input.getValue());
        if (isUpdate) {
            ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
            res = Response.status(Response.Status.OK).entity(serverResponse).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
            res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
        }
        return res;
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("rateId") int id) {
        Response res = null;
        boolean isDeleted = rateController.delete(id);
        if (isDeleted) {
            ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
            res = Response.status(Response.Status.OK).entity(serverResponse).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
            res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
        }
        return res;
    }

    @GET
    @Path("/list")
    public Response list() {
        List<RateModel> output = rateController.getList();
        return Response.status(Response.Status.OK).entity(output).build();
    }

    @GET
    public Response find(@QueryParam("rateId") int id) {
        List<RateModel> output = rateController.get(id);
        return Response.status(Response.Status.OK).entity(output).build();
    }
}
