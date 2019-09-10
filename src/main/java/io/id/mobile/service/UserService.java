/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.mobile.service;

import io.id.app.controller.UserController;
import io.id.app.rest.model.AuthenticateModel;
import io.id.app.rest.model.ServerResponse;
import io.id.app.rest.service.BaseService;
import io.id.app.model.UserDetailsModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("mobile")
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends BaseService {

    private UserController userController;

    public UserService() {
        log = getLogger(this.getClass());
        this.userController = new UserController();
    }

    @Path("/userdetails")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserDetails(AuthenticateModel input) {
        Response res;
        List<UserDetailsModel> data = userController.userDetails(input.getUname(), input.getPassword());

        if (!data.isEmpty()) {
            res = Response.status(Response.Status.OK).entity(data).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Username does not exist!");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }

}
