/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import com.codahale.metrics.annotation.Timed;
import io.id.app.controller.AdminController;
import io.id.app.model.UserModel;

import io.id.app.rest.model.AuthenticateModel;
import io.id.app.rest.model.ServerResponse;
import io.id.app.rest.model.UserRequestModel;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminService extends BaseService {

    private AdminController userController;

    public AdminService() {
        log = getLogger(this.getClass());
        this.userController = new AdminController();
    }

//    @GET
//    @Path("/user/getalldetail")
//    @Timed
//    public Response getAllUserDetail() {
//        Response res;
//        List<UserModel> data = userController.getAllUserDetails();
//        if (!data.isEmpty()) {
//            res = buildResponse(Response.Status.OK, data);
//        } else {
//            res = buildResponse(Response.Status.NO_CONTENT, data);
//        }
//        return res;
//    }
    
    @GET
    @Path("/user/getalluser")
    @Timed
    public Response getAllUser() {
        Response res;
        List<UserModel> data = userController.getAllUser();
        if (!data.isEmpty()) {
            res = buildResponse(Response.Status.OK, data);
        } else {
            res = buildResponse(Response.Status.NO_CONTENT, data);
        }

        return res;
    }
    
    

//    @POST
//    @Path("/adduser")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response userRegistration(UserRequestModel input) {
//        Response res;
//        boolean usernameIsExist = userController.checkUsername(input.getUsername());
//        if (!usernameIsExist) {
//            boolean emailIsExist = userController.checkEmail(input.getEmail());
//            if (!emailIsExist) {
//                boolean isValid = userController.userRegistration(input.getName(), input.getUsername(), input.getEmail(), input.getPhone(), input.getPassword(), input.getRole());
//                if (isValid) {
//                    ServerResponse serverResponse = new ServerResponse(Response.Status.CREATED, "Success");
//                    res = Response.status(Response.Status.CREATED).entity(serverResponse).build();
//                } else {
//                    ServerResponse serverResponse = new ServerResponse(Response.Status.BAD_REQUEST, "Failed to create new user");
//                    res = Response.status(Response.Status.BAD_REQUEST).entity(serverResponse).build();
//                }
//            } else {
//                ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Email address already in used");
//                res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
//            }
//        } else {
//            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Username already in used");
//            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
//        }
//
//        return res;
//    }
}
