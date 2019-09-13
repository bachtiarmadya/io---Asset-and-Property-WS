/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author permadi
 */
@ApiModel(description = "Authentication model")
public class AuthenticateModel {

    @JsonProperty("uname")
    @ApiModelProperty(name = "uname", value = "Username/email for user login", required = true)
    private String uname;
    @JsonProperty("password")
    @ApiModelProperty(name = "password", value = "Password for user login", required = true)
    private String password;

    public AuthenticateModel() {
    }

    public AuthenticateModel(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
