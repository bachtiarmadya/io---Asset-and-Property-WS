/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import io.id.app.configuration.ApplicationConfiguration;
import io.id.app.controller.TokenController;
import io.id.app.rest.model.ServerResponse;
import javax.ws.rs.Consumes;
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
@Path("token")
@Produces(MediaType.APPLICATION_JSON)
public class TokenService extends BaseService {

    private TokenController tokenController;

    public TokenService() {
        log = getLogger(this.getClass());
        this.tokenController = new TokenController();
    }

    @POST
    @Path("/validate/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateToken(@PathParam("token") String token) {
        Response res;
        int expiry = Integer.parseInt(getConfig(ApplicationConfiguration.TOKEN_EXPIRY));
        boolean isValid = tokenController.validateToken(token);
        if (isValid) {
            boolean isExpired = tokenController.validateToken(token, expiry);
            if (isExpired) {
                ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
                res = Response.status(Response.Status.OK).entity(serverResponse).build();
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Token expired");
                res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Invalid token");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }

}
