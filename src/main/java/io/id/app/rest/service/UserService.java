/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import com.codahale.metrics.annotation.Timed;
import io.id.app.controller.UserController;
import io.id.app.model.Sysuser;
import io.id.app.model.UserDetailsModel;
import io.id.app.model.UserModel;
import io.id.app.request.UserRequest;
import io.id.app.rest.model.ServerResponse;
import io.swagger.annotations.Api;
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
@Api
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends BaseService {

    private UserController userController;

    public UserService() {
        log = getLogger(this.getClass());
        this.userController = new UserController();
    }

    @GET
    @Path("/profile")
    @Timed
    public Response getAllUser(UserRequest input) {
        Response res;
        List<UserDetailsModel> data = userController.userDetails(input);
        if (!data.isEmpty()) {
            res = buildResponse(Response.Status.OK, data);
        } else {
            res = buildResponse(Response.Status.NO_CONTENT, data);
        }

        return res;
    }

    @POST
    @Path("/activate/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(@PathParam("id") int id, Sysuser user) {
        Response res = null;
        boolean isCreate = userController.addUser(user, id);
        if (isCreate) {
            boolean isActive = userController.activateMember(id);
            if (isActive) {
                ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
                res = Response.status(Response.Status.OK).entity(serverResponse).build();
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
            res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
        }
        return res;
    }

}
