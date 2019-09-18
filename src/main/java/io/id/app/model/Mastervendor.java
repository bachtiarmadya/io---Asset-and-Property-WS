/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.model;

import java.util.Date;

/**
 *
 * @author permadi
 */
public class Mastervendor {

    private Integer vendorid;

    private String vendorcode;

    private String vendorname;

    private String contact;

    private String email;

    private String address;

    private String note;

    private int rateid;

    private String registered;

    public Mastervendor() {
    }

    public Integer getVendorid() {
        return vendorid;
    }

    public void setVendorid(Integer vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorcode() {
        return vendorcode;
    }

    public void setVendorcode(String vendorcode) {
        this.vendorcode = vendorcode;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRateid() {
        return rateid;
    }

    public void setRateid(int rateid) {
        this.rateid = rateid;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public Mastervendor(Integer vendorid, String vendorcode, String vendorname, String contact, String email, String address, String note, int rateid, String registered) {
        this.vendorid = vendorid;
        this.vendorcode = vendorcode;
        this.vendorname = vendorname;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.note = note;
        this.rateid = rateid;
        this.registered = registered;
    }

}
