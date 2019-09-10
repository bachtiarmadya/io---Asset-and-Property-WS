/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import io.id.app.controller.ProvinceController;
import io.id.app.model.ProvinceModel;
import io.id.app.request.AddProvinceRequest;
import io.id.app.request.EditProvinceRequest;
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
@Path("province")
@Produces(MediaType.APPLICATION_JSON)
public class ProvinceService extends BaseService {

    private ProvinceController provinceController;

    public ProvinceService() {
        log = getLogger(this.getClass());
        this.provinceController = new ProvinceController();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AddProvinceRequest input) {
        Response res = null;
        boolean isCreate = provinceController.create(input.getProvincecode(), input.getProvincename());
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
    public Response update(EditProvinceRequest input) {
        Response res = null;
        boolean isUpdate = provinceController.update(input.getProvinceid(), input.getProvincecode(), input.getProvincename(), input.getIsactive());
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
    public Response delete(@QueryParam("provinceId") int provinceId) {
        Response res = null;
        boolean isDeleted = provinceController.delete(provinceId);
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
    public Response getList() {
        List<ProvinceModel> output = provinceController.getList();
        return Response.status(Response.Status.OK).entity(output).build();
    }

    @GET
    public Response get(@QueryParam("provinceId") int provinceId) {
        List<ProvinceModel> output = provinceController.find(provinceId);
        return Response.status(Response.Status.OK).entity(output).build();
    }

}
