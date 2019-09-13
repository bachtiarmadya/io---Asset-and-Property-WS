/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.mobile.service;

import io.id.app.controller.AssetController;
import io.id.app.rest.model.AssetRequest;
import io.id.app.rest.model.ServerResponse;
import io.id.app.rest.service.BaseService;
import io.id.app.model.AssetDetailsModel;
import io.id.app.model.AssetInstallRegisterModel;
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
@Path("mobile")
@Produces(MediaType.APPLICATION_JSON)
public class AssetService extends BaseService {

    private AssetController assetController;

    public AssetService() {
        log = getLogger(this.getClass());
        this.assetController = new AssetController();
    }

    @Path("/assetlist")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAsset() {
        Response res;
        List<AssetDetailsModel> data = assetController.getAllAsset();

        if (!data.isEmpty()) {
            res = Response.status(Response.Status.OK).entity(data).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Asset does not exist!");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }

    @Path("/asset")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findAllAsset(AssetRequest input) {
        Response res;
        List<AssetDetailsModel> data = assetController.findAsset(input.getAssetCode());

        if (!data.isEmpty()) {
            res = Response.status(Response.Status.OK).entity(data).build();
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Asset does not exist!");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }

    @Path("/asset/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerInstallatinAsset(AssetInstallRegisterModel input) {
        Response res;
        
        boolean isCreate = assetController.registerAsset(input.getLocationname(), input.getAssetCode(), input.getBuildingname(), input.getMembercode(), input.getRateid(), input.getGeolocation(), input.getPhoto(), input.getNote());
        if(isCreate)
        {
            ServerResponse serverResponse = new ServerResponse(Response.Status.CREATED, "Success");
            res = Response.status(Response.Status.CREATED).entity(serverResponse).build();
        }
        else
        {
            ServerResponse serverResponse = new ServerResponse(Response.Status.EXPECTATION_FAILED, "Expectation failed");
            res = Response.status(Response.Status.EXPECTATION_FAILED).entity(serverResponse).build();
        }
        return res;
    }

}
