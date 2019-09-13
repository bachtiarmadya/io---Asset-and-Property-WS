/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import com.codahale.metrics.annotation.Timed;
import io.id.app.controller.RoleController;
import io.id.app.rest.model.RoleModel;
import io.swagger.annotations.Api;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Api
@Path("roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleService extends BaseService {

    private RoleController roleController;

    public RoleService() {
        log = getLogger(this.getClass());
        this.roleController = new RoleController();
    }

    @GET
    @Path("/getall")
    @Timed
    public Response getAllRole() {
        Response res;
        List<RoleModel> data = roleController.getAll();
        if (!data.isEmpty()) {
            res = buildResponse(Response.Status.OK, data);
        } else {
            res = buildResponse(Response.Status.NO_CONTENT, data);
        }
        return res;
    }
    

}
