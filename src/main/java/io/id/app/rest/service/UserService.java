/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import io.swagger.annotations.Api;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author permadi
 */
@Api
@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends BaseService {

    public UserService() {
        log = getLogger(this.getClass());
    }

}
