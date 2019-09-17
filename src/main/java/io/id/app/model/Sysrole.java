/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.model;

/**
 *
 * @author permadi
 */

public class Sysrole  {
    private Integer roleid;
    private String rolecode;
    private String rolename;
    private String description;
    private short isactive;

    public Sysrole() {
    }

    public Sysrole(Integer roleid) {
        this.roleid = roleid;
    }

    public Sysrole(Integer roleid, short isactive) {
        this.roleid = roleid;
        this.isactive = isactive;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
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

    public short getIsactive() {
        return isactive;
    }

    public void setIsactive(short isactive) {
        this.isactive = isactive;
    }

    
}
