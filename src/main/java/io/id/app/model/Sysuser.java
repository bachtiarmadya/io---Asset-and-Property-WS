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

public class Sysuser  {

    private Integer userid;
    private String username;
    private String password;
    private String alias;
    private short isactive;
    private int departmentmemberid;
    private int roleid;

    public Sysuser() {
    }

    public Sysuser(Integer userid, String username, String password, String alias, short isactive, int departmentmemberid, int roleid) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.alias = alias;
        this.isactive = isactive;
        this.departmentmemberid = departmentmemberid;
        this.roleid = roleid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
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

    public short getIsactive() {
        return isactive;
    }

    public void setIsactive(short isactive) {
        this.isactive = isactive;
    }

    public int getDepartmentmemberid() {
        return departmentmemberid;
    }

    public void setDepartmentmemberid(int departmentmemberid) {
        this.departmentmemberid = departmentmemberid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }


    
}
