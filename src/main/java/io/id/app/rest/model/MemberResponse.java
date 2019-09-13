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
@ApiModel(description = "Server response model")
public class MemberResponse {

    @JsonProperty("code")
    @ApiModelProperty(name = "code", value = "Server response code", required = true)
    private int code;
    @JsonProperty("message")
    @ApiModelProperty(name = "message", value = "Server response message", required = true)
    private String message;
    @JsonProperty("memberid")
    @ApiModelProperty(name = "memberid", value = "member id response message", required = true)
    private String memberid;

    public MemberResponse() {
    }

    public MemberResponse(Response.Status responseStatus) {
        code = responseStatus.getStatusCode();
        message = responseStatus.getReasonPhrase();
    }

    public MemberResponse(Response.Status responseStatus, String message, String memberid) {
        code = responseStatus.getStatusCode();
        this.message = message;
        this.memberid = memberid;
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

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

}
