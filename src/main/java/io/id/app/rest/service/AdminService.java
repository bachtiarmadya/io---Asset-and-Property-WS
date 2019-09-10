/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import com.codahale.metrics.annotation.Timed;
import io.id.app.controller.AdminController;
import io.id.app.model.MemberModel;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminService extends BaseService {

    private AdminController adminController;

    public AdminService() {
        log = getLogger(this.getClass());
        this.adminController = new AdminController();
    }

    @GET
    @Path("/getmember")
    @Timed
    public Response getAllMember() {
        Response res;
        List<MemberModel> data = adminController.getAllMember();
        if (!data.isEmpty()) {
            res = buildResponse(Response.Status.OK, data);
        } else {
            res = buildResponse(Response.Status.NO_CONTENT, data);
        }
        return res;
    }

    @GET
    @Path("/user/getalluser")
    @Timed
    public Response getAllUser() {
        Response res;
        List<UserModel> data = adminController.getAllUser();
        if (!data.isEmpty()) {
            res = buildResponse(Response.Status.OK, data);
        } else {
            res = buildResponse(Response.Status.NO_CONTENT, data);
        }

        return res;
    }

    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMember(MemberModel input) {
        Response res;
        boolean isExist = adminController.checkMemberCode(input.getMembercode());
        if (!isExist) {
            boolean isCreate = adminController.addNewMember(input.getMembercode(), input.getUsername(), input.getEmail(), input.getImageaddress(), input.getDescription(), input.getMemberlevelid(), input.getDepartmentid());
            if (isCreate) {
                ServerResponse serverResponse = new ServerResponse(Response.Status.CREATED, "Success");
                res = Response.status(Response.Status.CREATED).entity(serverResponse).build();
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.CONFLICT, "Member code already exist");
            res = Response.status(Response.Status.CONFLICT).entity(serverResponse).build();
        }
        return res;
    }

    @POST
    @Path("/user/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMember(@QueryParam("departmentmemberid") int id) {
        Response res;
        boolean isRemoved = adminController.deleteMember(id);
        if (isRemoved) {
            ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
            res = Response.status(Response.Status.OK).entity(serverResponse).build();

        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
            res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
        }
        return res;
    }
}
