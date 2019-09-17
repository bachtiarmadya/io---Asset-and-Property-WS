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
public class Masterdepartmentmember {

    private Integer departmentmemberid;
    private String membercode;
    private String membername;

    private String email;
    private String imageaddress;
    private String description;
    private short isactive;
    private int memberlevelid;

    public Masterdepartmentmember() {
    }

    public Masterdepartmentmember(Integer departmentmemberid, String membercode, String membername, String email, String imageaddress, String description, short isactive, int memberlevelid) {
        this.departmentmemberid = departmentmemberid;
        this.membercode = membercode;
        this.membername = membername;
        this.email = email;
        this.imageaddress = imageaddress;
        this.description = description;
        this.isactive = isactive;
        this.memberlevelid = memberlevelid;
    }

    public Integer getDepartmentmemberid() {
        return departmentmemberid;
    }

    public void setDepartmentmemberid(Integer departmentmemberid) {
        this.departmentmemberid = departmentmemberid;
    }

    public String getMembercode() {
        return membercode;
    }

    public void setMembercode(String membercode) {
        this.membercode = membercode;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
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

    public int getMemberlevelid() {
        return memberlevelid;
    }

    public void setMemberlevelid(int memberlevelid) {
        this.memberlevelid = memberlevelid;
    }


    
}
