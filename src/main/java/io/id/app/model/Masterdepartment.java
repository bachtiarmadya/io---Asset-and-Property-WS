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
public class Masterdepartment  {


    private String departmentcode;
    private String departmentname;
    private String description;
    private short isactive;

    private Integer departmentid;

    public Masterdepartment() {
    }

    public Masterdepartment(String departmentcode, String departmentname, String description, short isactive, Integer departmentid) {
        this.departmentcode = departmentcode;
        this.departmentname = departmentname;
        this.description = description;
        this.isactive = isactive;
        this.departmentid = departmentid;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
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

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }


    
}
