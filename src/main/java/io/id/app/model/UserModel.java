/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author permadi
 */
@ApiModel(description = "User information Model")
public class UserModel {

    @JsonProperty("userId")
    @ApiModelProperty(name = "userId", value = "Id of user", required = true)
    private int userid;
    @JsonProperty("username")
    @ApiModelProperty(name = "username", value = "username of user", required = true)
    private String username;
    @JsonProperty("password")
    @ApiModelProperty(name = "password", value = "password of user", required = true)
    private String password;
    @JsonProperty("alias")
    @ApiModelProperty(name = "alias", value = "alias of user", required = true)
    private String alias;
    @JsonProperty("roleId")
    @ApiModelProperty(name = "roleId", value = "Id of user role", required = true)
    private int roleid;
    @JsonProperty("departmentMemberId")
    @ApiModelProperty(name = "departmentMemberId", value = "Id of departement member", required = true)
    private int departmentmemberid;
    @JsonProperty("isActive")
    @ApiModelProperty(name = "isActive", value = "is Account activate", required = true)
    private int isactive;

    public UserModel() {
    }

    public UserModel(int userid, String username, String password, String alias, int roleid, int departmentmemberid, int isactive) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.alias = alias;
        this.roleid = roleid;
        this.departmentmemberid = departmentmemberid;
        this.isactive = isactive;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getDepartmentmemberid() {
        return departmentmemberid;
    }

    public void setDepartmentmemberid(int departmentmemberid) {
        this.departmentmemberid = departmentmemberid;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

}
