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
public class MemberModel {
    
    private int departmentmemberid;
    private String membercode;
    private String username;
    private String email;
    private String imageaddress;
    private String description;
    private int memberlevelid;
    private int departmentid;
    private int isactive;

    public MemberModel() {
    }

    public MemberModel(int departmentmemberid, String membercode, String username, String email, String imageaddress, String description, int memberlevelid, int departmentid, int isactive) {
        this.departmentmemberid = departmentmemberid;
        this.membercode = membercode;
        this.username = username;
        this.email = email;
        this.imageaddress = imageaddress;
        this.description = description;
        this.memberlevelid = memberlevelid;
        this.departmentid = departmentid;
        this.isactive = isactive;
    }

    public int getDepartmentmemberid() {
        return departmentmemberid;
    }

    public void setDepartmentmemberid(int departmentmemberid) {
        this.departmentmemberid = departmentmemberid;
    }

    public String getMembercode() {
        return membercode;
    }

    public void setMembercode(String membercode) {
        this.membercode = membercode;
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

    public int getMemberlevelid() {
        return memberlevelid;
    }

    public void setMemberlevelid(int memberlevelid) {
        this.memberlevelid = memberlevelid;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }
    
    
    
}
