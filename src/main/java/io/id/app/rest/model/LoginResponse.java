/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@ApiModel(description = "Login response model")
public class LoginResponse {

    @JsonProperty("code")
    @ApiModelProperty(name = "code", value = "Login response code", required = true)
    private int code;

    @JsonProperty("message")
    @ApiModelProperty(name = "message", value = "Login response message", required = true)
    private String message;

    @JsonProperty("login")
    @ApiModelProperty(name = "loginAs", value = "User type", required = true)
    private String loginAs;

    public LoginResponse() {
    }

    public LoginResponse(Response.Status responseStatus) {
        code = responseStatus.getStatusCode();
        message = responseStatus.getReasonPhrase();
    }

    public LoginResponse(Response.Status responseStatus, String message, String loginAs) {

        code = responseStatus.getStatusCode();;
        this.message = message;
        this.loginAs = loginAs;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoginAs() {
        return loginAs;
    }

    public void setLoginAs(String loginAs) {
        this.loginAs = loginAs;
    }

}
