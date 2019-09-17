/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import com.codahale.metrics.annotation.Timed;
import io.id.app.controller.MemberLevelController;
import io.id.app.model.Mastermemberlevel;
import io.id.app.rest.model.ServerResponse;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("/level")
@Produces(MediaType.APPLICATION_JSON)
public class MemberLevelService extends BaseService {

    private MemberLevelController levelController;

    public MemberLevelService() {
        this.levelController = new MemberLevelController();
        log = getLogger(this.getClass());
    }

    @GET
    @Path("/getall")
    @Timed
    public Response getAllRole() {
        Response res;
        List<Mastermemberlevel> data = levelController.getAll();
        if (!data.isEmpty()) {
            res = buildResponse(Response.Status.OK, data);
        } else {
            res = buildResponse(Response.Status.NO_CONTENT, data);
        }
        return res;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Mastermemberlevel input) {
        Response res = null;
        boolean isCreate = levelController.add(input);
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
    public Response update(Mastermemberlevel input) {
        Response res = null;
        boolean isCreate = levelController.update(input);
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
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Response res = null;
        boolean isCreate = levelController.delete(id);
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
    @Path("/activate/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(@PathParam("id") int id) {
        Response res = null;
        boolean isCreate = levelController.activate(id);
        if (isCreate) {
            ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
            res = Response.status(Response.Status.OK).entity(serverResponse).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
            res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
        }
        return res;
    }

}
