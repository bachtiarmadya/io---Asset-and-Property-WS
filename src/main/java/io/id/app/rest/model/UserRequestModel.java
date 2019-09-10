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
@ApiModel(description = "User model")
public class UserRequestModel {

    @JsonProperty("name")
    @ApiModelProperty(name = "name", value = "Account name", required = true)
    private String name;
    @JsonProperty("username")
    @ApiModelProperty(name = "username", value = "Username for account", required = true)
    private String username;
    @JsonProperty("email")
    @ApiModelProperty(name = "email", value = "Email for account", required = true)
    private String email;
    @JsonProperty("phone")
    @ApiModelProperty(name = "phone", value = "Phone for account", required = true)
    private String phone;
    @JsonProperty("password")
    @ApiModelProperty(name = "password", value = "Password for account", required = true)
    private String password;
    @JsonProperty("role")
    @ApiModelProperty(name = "role", value = "Role for account", required = true)
    private int role;

    public UserRequestModel() {
    }

    public UserRequestModel(String name, String username, String email, String phone, String password, int role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    

}
