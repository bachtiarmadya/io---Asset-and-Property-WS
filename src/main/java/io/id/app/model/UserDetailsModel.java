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
public class UserDetailsModel {
    
    @JsonProperty("username")
    @ApiModelProperty(name = "username", value = " ", required = true)
    private String username;
    
    @JsonProperty("memberName")
    @ApiModelProperty(name = "memberName", value = " ", required = true)
    private String membername;
    
    @JsonProperty("role")
    @ApiModelProperty(name = "role", value = " ", required = true)
    private String rolename;
    
    @JsonProperty("memberCode")
    @ApiModelProperty(name = "memberCode", value = " ", required = true)
    private String membercode;
    
    @JsonProperty("email")
    @ApiModelProperty(name = "email", value = " ", required = true)
    private String email;
    
    @JsonProperty("image")
    @ApiModelProperty(name = "image", value = " ", required = true)
    private String imageaddress;
    
    @JsonProperty("level")
    @ApiModelProperty(name = "level", value = " ", required = true)
    private String levelname;
    
    @JsonProperty("department")
    @ApiModelProperty(name = "department", value = " ", required = true)
    private String departmentname;

    public UserDetailsModel() {
    }

    public UserDetailsModel(String username, String membername, String rolename, String membercode, String email, String imageaddress, String levelname, String departmentname) {
        this.username = username;
        this.membername = membername;
        this.rolename = rolename;
        this.membercode = membercode;
        this.email = email;
        this.imageaddress = imageaddress;
        this.levelname = levelname;
        this.departmentname = departmentname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getMembercode() {
        return membercode;
    }

    public void setMembercode(String membercode) {
        this.membercode = membercode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageaddress() {
        return imageaddress;
    }

    public void setImageaddress(String imageaddress) {
        this.imageaddress = imageaddress;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    
    
            
    
}
