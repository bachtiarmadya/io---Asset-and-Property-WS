/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.mobile.service;

import io.id.app.controller.LoginController;
import io.id.app.rest.model.AuthenticateModel;
import io.id.app.rest.model.LoginResponse;
import io.id.app.rest.model.ServerResponse;
import io.id.app.rest.service.BaseService;
import java.sql.SQLException;
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
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginService extends BaseService{
    
    private LoginController loginController;

    public LoginService() {
        log = getLogger(this.getClass());
        this.loginController = new LoginController();
    }
    
    @Path("/mobile")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response mobileLogin(AuthenticateModel input) throws SQLException {
        Response res;
        boolean isExist = loginController.checkUsername(input.getUname());
        if (isExist) {

            String isValid = loginController.loginMobile(input);
            if (isValid != null) {
                
                ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
                res = Response.status(Response.Status.OK).entity(serverResponse).build();
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Password does not match");
                res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
            }

        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Username does not exist!");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }
    
    
}
