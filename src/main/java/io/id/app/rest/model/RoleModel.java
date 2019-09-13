/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.model;

/**
 *
 * @author permadi
 */
public class RoleModel {

    private int roleid;
    private String rolecode;
    private String rolename;
    private String description;
    private int isactive;

    public RoleModel() {
    }

    public RoleModel(int roleid, String rolecode, String rolename, String description, int isactive) {
        this.roleid = roleid;
        this.rolecode = rolecode;
        this.rolename = rolename;
        this.description = description;
        this.isactive = isactive;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }
    
    
}
